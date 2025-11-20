package com.turkcell.book_service.application.author.command;

import java.util.UUID;

import com.turkcell.book_service.application.author.dto.DeletedAuthorResponse;
import com.turkcell.book_service.core.cqrs.Command;

import jakarta.validation.constraints.NotBlank;

public record DeleteAuthorCommand(@NotBlank UUID id) implements Command<DeletedAuthorResponse> {

}
