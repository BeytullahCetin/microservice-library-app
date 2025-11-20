package com.turkcell.book_service.application.bookcopy.query;

import java.util.UUID;

import com.turkcell.book_service.application.bookcopy.dto.BookCopyResponse;
import com.turkcell.book_service.core.cqrs.Query;

import jakarta.validation.constraints.NotNull;

public record FindByIdBookCopyQuery(
		@NotNull UUID id)
		implements Query<BookCopyResponse> {

}
