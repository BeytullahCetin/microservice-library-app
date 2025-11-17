package com.turkcell.borrow_service.dto.response;

import java.time.LocalDate;
import java.util.UUID;

public record BorrowResponse(
		UUID id,
		UUID customerId,
		UUID bookCopyId,
		LocalDate borrowDate,
		LocalDate dueDate,
		LocalDate returnDate) {
}

