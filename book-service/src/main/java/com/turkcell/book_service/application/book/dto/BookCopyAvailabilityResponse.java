package com.turkcell.book_service.application.book.dto;

import java.util.UUID;

public record BookCopyAvailabilityResponse(
		UUID id,
		boolean availableToBorrow,
		UUID bookCopyId) {
}
