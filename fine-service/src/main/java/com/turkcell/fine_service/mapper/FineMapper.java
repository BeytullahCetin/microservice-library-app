package com.turkcell.fine_service.mapper;

import com.turkcell.fine_service.dto.request.CreateFineRequest;
import com.turkcell.fine_service.dto.response.FineResponse;
import com.turkcell.fine_service.entity.Fine;
import org.springframework.stereotype.Component;

@Component
public class FineMapper {

	public Fine toFine(CreateFineRequest request) {
		return Fine.builder()
				.borrowId(request.borrowId())
				.fineType(request.fineType())
				.amount(request.amount())
				.issueDate(request.issueDate())
				.paymentDate(null)
				.paid(false)
				.build();
	}

	public FineResponse toResponse(Fine fine) {
		return new FineResponse(
				fine.getId(),
				fine.getBorrowId(),
				fine.getFineType(),
				fine.getAmount(),
				fine.getIssueDate(),
				fine.getPaymentDate(),
				fine.isPaid());
	}
}

