package com.turkcell.book_service.application.bookcopy.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreatedBookCopyResponse(
		UUID id,
		LocalDateTime acquisitionDate,
		String bookStatus,
		UUID bookId) {
}
