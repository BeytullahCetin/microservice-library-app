package com.turkcell.book_service.domain.author.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public record AuthorId(UUID value) implements Serializable {

	public AuthorId {
		Objects.requireNonNull(value, "Value for AuthorId cannot be null!");
	}

	public static AuthorId generate() {
		return new AuthorId(UUID.randomUUID());
	}
}