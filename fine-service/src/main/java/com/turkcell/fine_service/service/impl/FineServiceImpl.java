package com.turkcell.fine_service.service.impl;

import com.turkcell.fine_service.dto.request.CreateFineRequest;
import com.turkcell.fine_service.dto.request.MarkFineAsPaidRequest;
import com.turkcell.fine_service.dto.response.FineResponse;
import com.turkcell.fine_service.entity.Fine;
import com.turkcell.fine_service.exception.BusinessException;
import com.turkcell.fine_service.exception.NotFoundException;
import com.turkcell.fine_service.mapper.FineMapper;
import com.turkcell.fine_service.repository.FineRepository;
import com.turkcell.fine_service.service.FineService;
import com.turkcell.fine_service.service.support.RemoteReferenceValidator;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FineServiceImpl implements FineService {

	private final FineRepository fineRepository;
	private final FineMapper fineMapper;
	private final RemoteReferenceValidator referenceValidator;

	@Override
	@Transactional
	public FineResponse createFine(CreateFineRequest request) {
		if (request.issueDate().isAfter(LocalDate.now())) {
			throw new BusinessException("INVALID_ISSUE_DATE", "Issue date cannot be in the future");
		}

		referenceValidator.assertBorrowExists(request.borrowId());

		Fine fine = fineMapper.toFine(request);
		Fine saved = fineRepository.save(fine);
		return fineMapper.toResponse(saved);
	}

	@Override
	@Transactional(readOnly = true)
	public FineResponse getFine(UUID id) {
		return fineRepository.findById(id)
				.map(fineMapper::toResponse)
				.orElseThrow(() -> new NotFoundException("FINE_NOT_FOUND", "Fine not found"));
	}

	@Override
	@Transactional(readOnly = true)
	public List<FineResponse> getFinesForBorrow(UUID borrowId) {
		return fineRepository.findByBorrowIdOrderByIssueDateDesc(borrowId)
				.stream()
				.map(fineMapper::toResponse)
				.toList();
	}

	@Override
	@Transactional(readOnly = true)
	public List<FineResponse> getOutstandingFines() {
		return fineRepository.findByPaidFalseOrderByIssueDateAsc()
				.stream()
				.map(fineMapper::toResponse)
				.toList();
	}

	@Override
	@Transactional
	public FineResponse markFineAsPaid(UUID fineId, MarkFineAsPaidRequest request) {
		Fine fine = fineRepository.findById(fineId)
				.orElseThrow(() -> new NotFoundException("FINE_NOT_FOUND", "Fine not found"));

		if (fine.isPaid()) {
			throw new BusinessException("FINE_ALREADY_PAID", "Fine already marked as paid");
		}

		if (request.paymentDate().isBefore(fine.getIssueDate())) {
			throw new BusinessException("INVALID_PAYMENT_DATE", "Payment date cannot be before issue date");
		}

		fine.setPaid(true);
		fine.setPaymentDate(request.paymentDate());
		return fineMapper.toResponse(fineRepository.save(fine));
	}
}

