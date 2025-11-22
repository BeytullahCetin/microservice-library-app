package com.turkcell.book_service.application.bookcopy.command;

import java.util.UUID;

import com.turkcell.book_service.application.bookcopy.dto.ReturnedBookCopyResponse;
import com.turkcell.book_service.core.cqrs.Command;

import jakarta.validation.constraints.NotNull;

public record ReturnBookCopyCommand(
		@NotNull UUID id)
		implements Command<ReturnedBookCopyResponse> {
}
