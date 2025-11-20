package com.turkcell.book_service.domain.language.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public record LanguageId(UUID value) implements Serializable {
	public LanguageId {
		Objects.requireNonNull(value, "Value for LanguageId cannot be null!");
	}

	public static LanguageId generate() {
		return new LanguageId(UUID.randomUUID());
	}
}
