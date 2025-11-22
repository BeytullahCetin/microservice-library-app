package com.turkcell.book_service.application.bookcopy.command;

import java.util.UUID;

import com.turkcell.book_service.application.bookcopy.dto.BorrowedBookCopyResponse;
import com.turkcell.book_service.core.cqrs.Command;

import jakarta.validation.constraints.NotNull;

public record BorrowBookCopyCommand(
		@NotNull UUID id)
		implements Command<BorrowedBookCopyResponse> {
}
