package com.turkcell.fine_service.dto.request;

import com.turkcell.fine_service.entity.enums.FineType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record CreateFineRequest(
		@NotNull UUID borrowId,
		@NotNull FineType fineType,
		@NotNull @DecimalMin(value = "0.01") @Digits(integer = 10, fraction = 2) BigDecimal amount,
		@NotNull LocalDate issueDate) {
}

