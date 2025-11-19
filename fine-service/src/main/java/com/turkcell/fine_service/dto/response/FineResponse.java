package com.turkcell.fine_service.dto.response;

import com.turkcell.fine_service.entity.enums.FineType;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record FineResponse(
		UUID id,
		UUID borrowId,
		FineType fineType,
		BigDecimal amount,
		LocalDate issueDate,
		LocalDate paymentDate,
		boolean paid) {
}

