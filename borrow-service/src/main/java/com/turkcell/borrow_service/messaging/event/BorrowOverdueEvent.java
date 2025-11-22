package com.turkcell.borrow_service.messaging.event;

import java.time.LocalDate;
import java.util.UUID;

public record BorrowOverdueEvent(
		UUID borrowId,
		UUID customerId,
		UUID bookCopyId,
		LocalDate borrowDate,
		LocalDate dueDate,
		LocalDate returnDate,
		long daysOverdue) {
}

