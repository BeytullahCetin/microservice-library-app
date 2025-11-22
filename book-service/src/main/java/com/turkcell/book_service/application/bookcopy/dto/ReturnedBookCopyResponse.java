package com.turkcell.book_service.application.bookcopy.dto;

import java.util.UUID;

public record ReturnedBookCopyResponse(
		UUID id,
		String bookStatus) {
}
