package com.turkcell.book_service.application.book.command;

import com.turkcell.book_service.application.book.dto.DeletedBookResponse;
import com.turkcell.book_service.core.cqrs.Command;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record DeleteBookCommand(@NotBlank UUID id) implements Command<DeletedBookResponse> {
}
