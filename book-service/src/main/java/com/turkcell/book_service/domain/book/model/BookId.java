package com.turkcell.book_service.domain.book.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public record BookId(UUID value) implements Serializable {

	public BookId {
		Objects.requireNonNull(value, "Value for AuthorId cannot be null!");
	}

	public static BookId generate() {
		return new BookId(UUID.randomUUID());
	}
}
