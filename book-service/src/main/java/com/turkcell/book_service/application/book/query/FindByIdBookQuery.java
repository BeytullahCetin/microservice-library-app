package com.turkcell.book_service.application.book.query;

import com.turkcell.book_service.application.book.dto.BookResponse;
import com.turkcell.book_service.core.cqrs.Query;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record FindByIdBookQuery(@NotBlank UUID id) implements Query<BookResponse> {
}
