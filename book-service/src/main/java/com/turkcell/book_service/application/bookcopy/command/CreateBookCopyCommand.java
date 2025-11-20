package com.turkcell.book_service.application.bookcopy.command;

import java.time.LocalDateTime;
import java.util.UUID;

import com.turkcell.book_service.application.bookcopy.dto.CreatedBookCopyResponse;
import com.turkcell.book_service.core.cqrs.Command;

import jakarta.validation.constraints.NotNull;

public record CreateBookCopyCommand(
		@NotNull UUID bookId,
		@NotNull LocalDateTime acquisitionDate,
		@NotNull String bookStatus)
		implements Command<CreatedBookCopyResponse> {
}
