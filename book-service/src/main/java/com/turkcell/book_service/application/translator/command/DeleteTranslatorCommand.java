package com.turkcell.book_service.application.translator.command;

import java.util.UUID;

import com.turkcell.book_service.application.translator.dto.DeletedTranslatorResponse;
import com.turkcell.book_service.core.cqrs.Command;

import jakarta.validation.constraints.NotBlank;

public record DeleteTranslatorCommand(
		@NotBlank UUID id)
		implements Command<DeletedTranslatorResponse> {
}
