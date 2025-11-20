package com.turkcell.book_service.application.author.query;

import java.util.UUID;

import com.turkcell.book_service.application.author.dto.AuthorResponse;
import com.turkcell.book_service.core.cqrs.Query;

import jakarta.validation.constraints.NotBlank;

public record FindByIdAuthorQuery(
		@NotBlank UUID id)
		implements Query<AuthorResponse> {
}
