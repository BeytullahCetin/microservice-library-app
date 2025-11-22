package com.turkcell.borrow_service.service.impl;

import com.turkcell.borrow_service.client.BookCopyClient;
import com.turkcell.borrow_service.client.dto.BookCopyClientResponse;
import com.turkcell.borrow_service.config.BorrowServiceProperties;
import com.turkcell.borrow_service.dto.request.CompleteBorrowRequest;
import com.turkcell.borrow_service.dto.request.CreateBorrowRequest;
import com.turkcell.borrow_service.dto.request.CreateReservationRequest;
import com.turkcell.borrow_service.dto.response.BorrowOperationResponse;
import com.turkcell.borrow_service.dto.response.BorrowResponse;
import com.turkcell.borrow_service.dto.response.ReservationResponse;
import com.turkcell.borrow_service.entity.Borrow;
import com.turkcell.borrow_service.exception.BusinessException;
import com.turkcell.borrow_service.exception.NotFoundException;
import com.turkcell.borrow_service.mapper.BorrowMapper;
import com.turkcell.borrow_service.repository.BorrowRepository;
import com.turkcell.borrow_service.service.BorrowService;
import com.turkcell.borrow_service.service.ReservationService;
import com.turkcell.borrow_service.service.support.RemoteReferenceValidator;
import feign.FeignException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BorrowServiceImpl implements BorrowService {

	private final BorrowRepository borrowRepository;
	private final BorrowMapper borrowMapper;
	private final RemoteReferenceValidator referenceValidator;
	private final ReservationService reservationService;
	private final BookCopyClient bookCopyClient;
	private final BorrowServiceProperties borrowServiceProperties;

	@Override
	@Transactional
	public BorrowOperationResponse createBorrow(CreateBorrowRequest request) {
		validateDates(request.borrowDate(), request.dueDate());
		referenceValidator.assertCustomerExists(request.customerId());
		referenceValidator.assertBookCopyExists(request.bookCopyId());

		if (borrowRepository.existsByBookCopyIdAndReturnDateIsNull(request.bookCopyId())) {
			ReservationResponse reservationResponse = createReservationForUnavailableBook(request);
			return BorrowOperationResponse.reservationCreated(
					reservationResponse,
					"Requested book copy is not available. Reservation created instead.");
		}

		Borrow borrow = borrowMapper.toBorrow(request);
		@SuppressWarnings("null")
		Borrow savedBorrow = borrowRepository.save(borrow);
		return BorrowOperationResponse.borrowCreated(borrowMapper.toResponse(savedBorrow));
	}

	@Override
	@Transactional(readOnly = true)
	public BorrowResponse getBorrow(UUID id) {
		@SuppressWarnings("null")
		Borrow borrow = borrowRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("BORROW_NOT_FOUND", "Borrow record not found"));
		return borrowMapper.toResponse(borrow);
	}

	@Override
	@Transactional(readOnly = true)
	public List<BorrowResponse> getBorrows(boolean activeOnly) {
		List<Borrow> borrows = activeOnly ? borrowRepository.findByReturnDateIsNull() : borrowRepository.findAll();
		return borrows.stream().map(borrowMapper::toResponse).toList();
	}

	@Override
	@Transactional
	public BorrowResponse completeBorrow(UUID borrowId, CompleteBorrowRequest request) {
		@SuppressWarnings("null")
		Borrow borrow = borrowRepository.findById(borrowId)
				.orElseThrow(() -> new NotFoundException("BORROW_NOT_FOUND", "Borrow record not found"));

		if (borrow.getReturnDate() != null) {
			throw new BusinessException("BORROW_ALREADY_CLOSED", "Borrow record already returned");
		}

		LocalDate returnDate = request.returnDate();
		if (returnDate.isBefore(borrow.getBorrowDate())) {
			throw new BusinessException("INVALID_RETURN_DATE", "Return date cannot be before borrow date");
		}

		borrow.setReturnDate(returnDate);
		return borrowMapper.toResponse(borrowRepository.save(borrow));
	}

	private void validateDates(LocalDate borrowDate, LocalDate dueDate) {
		if (dueDate.isBefore(borrowDate)) {
			throw new BusinessException("INVALID_DUE_DATE", "Due date must be after borrow date");
		}
	}

	private ReservationResponse createReservationForUnavailableBook(CreateBorrowRequest request) {
		UUID bookId = resolveBookId(request.bookCopyId());
		LocalDateTime expireAt = calculateReservationExpiration();
		CreateReservationRequest reservationRequest = new CreateReservationRequest(
				request.customerId(),
				bookId,
				expireAt);
		return reservationService.createReservation(reservationRequest);
	}

	private UUID resolveBookId(UUID bookCopyId) {
		try {
			BookCopyClientResponse response = bookCopyClient.getBookCopy(bookCopyId);
			if (response.bookId() == null) {
				throw new BusinessException("BOOK_ID_MISSING", "Book information missing for book copy: " + bookCopyId);
			}
			return response.bookId();
		} catch (FeignException.NotFound exception) {
			throw new BusinessException("BOOK_COPY_NOT_FOUND", "Book copy not found: " + bookCopyId);
		} catch (FeignException exception) {
			throw new BusinessException("REMOTE_SERVICE_ERROR", exception.getMessage());
		}
	}

	private LocalDateTime calculateReservationExpiration() {
		Duration ttl = borrowServiceProperties.getReservation().getAutoHoldTtl();
		if (ttl == null || ttl.isZero() || ttl.isNegative()) {
			ttl = Duration.ofHours(24);
		}
		return LocalDateTime.now().plus(ttl);
	}
}

