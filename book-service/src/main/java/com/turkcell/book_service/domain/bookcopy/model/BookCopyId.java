package com.turkcell.book_service.domain.bookcopy.model;

import java.util.Objects;
import java.util.UUID;

public record BookCopyId(UUID value) {
	public BookCopyId {
		Objects.requireNonNull(value, "Value for BookCopyId cannot be null!");
	}

	public static BookCopyId generate() {
		return new BookCopyId(UUID.randomUUID());
	}
}
