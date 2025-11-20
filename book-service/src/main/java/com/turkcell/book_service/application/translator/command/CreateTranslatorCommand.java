package com.turkcell.book_service.application.translator.command;

import com.turkcell.book_service.application.translator.dto.CreatedTranslatorResponse;
import com.turkcell.book_service.core.cqrs.Command;

import jakarta.validation.constraints.NotBlank;

public record CreateTranslatorCommand(
		@NotBlank String name)
		implements Command<CreatedTranslatorResponse> {
}
