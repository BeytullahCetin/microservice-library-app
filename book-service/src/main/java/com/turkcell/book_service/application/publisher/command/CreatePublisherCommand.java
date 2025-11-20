package com.turkcell.book_service.application.publisher.command;

import com.turkcell.book_service.application.publisher.dto.CreatedPublisherResponse;
import com.turkcell.book_service.core.cqrs.Command;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreatePublisherCommand(
		@NotBlank @Size(max = 255) String name,
		@NotBlank @Size(max = 500) String address)
		implements Command<CreatedPublisherResponse> {
}
