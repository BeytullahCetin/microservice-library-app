package com.turkcell.borrow_service.dto.request;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

public record CreateBorrowRequest(
		@NotNull UUID customerId,
		@NotNull UUID bookId,
		@NotNull LocalDate borrowDate,
		@NotNull LocalDate dueDate) {
}

