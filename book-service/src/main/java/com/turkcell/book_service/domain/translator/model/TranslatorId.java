package com.turkcell.book_service.domain.translator.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public record TranslatorId(UUID value) implements Serializable {
	public TranslatorId {
		Objects.requireNonNull(value, "Value for ProductId cannot be null!");
	}

	public static TranslatorId generate() {
		return new TranslatorId(UUID.randomUUID());
	}
}
