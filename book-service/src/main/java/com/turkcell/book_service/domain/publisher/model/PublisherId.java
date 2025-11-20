package com.turkcell.book_service.domain.publisher.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public record PublisherId(UUID value) implements Serializable {
	public PublisherId {
		Objects.requireNonNull(value, "Value for PublisherId cannot be null!");
	}

	public static PublisherId generate() {
		return new PublisherId(UUID.randomUUID());
	}
}
