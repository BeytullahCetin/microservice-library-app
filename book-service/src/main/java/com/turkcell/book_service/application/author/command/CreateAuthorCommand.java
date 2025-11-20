package com.turkcell.book_service.application.author.command;

import com.turkcell.book_service.application.author.dto.CreatedAuthorResponse;
import com.turkcell.book_service.core.cqrs.Command;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateAuthorCommand(
		@NotBlank @Size(max = 255) String name)
		implements Command<CreatedAuthorResponse> {
}