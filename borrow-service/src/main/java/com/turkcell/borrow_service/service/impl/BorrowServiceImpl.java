package com.turkcell.borrow_service.service.impl;

import com.turkcell.borrow_service.dto.request.CompleteBorrowRequest;
import com.turkcell.borrow_service.dto.request.CreateBorrowRequest;
import com.turkcell.borrow_service.dto.response.BorrowResponse;
import com.turkcell.borrow_service.entity.Borrow;
import com.turkcell.borrow_service.exception.BusinessException;
import com.turkcell.borrow_service.exception.NotFoundException;
import com.turkcell.borrow_service.mapper.BorrowMapper;
import com.turkcell.borrow_service.repository.BorrowRepository;
import com.turkcell.borrow_service.service.BorrowService;
import com.turkcell.borrow_service.service.support.RemoteReferenceValidator;
import java.time.LocalDate;
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

	@Override
	@Transactional
	public BorrowResponse createBorrow(CreateBorrowRequest request) {
		validateDates(request.borrowDate(), request.dueDate());
		referenceValidator.assertCustomerExists(request.customerId());
		referenceValidator.assertBookCopyExists(request.bookCopyId());

		if (borrowRepository.existsByBookCopyIdAndReturnDateIsNull(request.bookCopyId())) {
			throw new BusinessException("BOOK_COPY_UNAVAILABLE", "Book copy already borrowed");
		}

		Borrow borrow = borrowMapper.toBorrow(request);
		Borrow savedBorrow = borrowRepository.save(borrow);
		return borrowMapper.toResponse(savedBorrow);
	}

	@Override
	@Transactional(readOnly = true)
	public BorrowResponse getBorrow(UUID id) {
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
}

