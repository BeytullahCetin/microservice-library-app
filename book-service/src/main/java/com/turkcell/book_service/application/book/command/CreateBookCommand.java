package com.turkcell.book_service.application.book.command;

import com.turkcell.book_service.application.book.dto.CreatedBookResponse;
import com.turkcell.book_service.core.cqrs.Command;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

public record CreateBookCommand(
        @NotBlank @Size(max = 255) String title,
        @NotBlank @Size(max = 255) String isbn,
        @Positive @Min(value = 0) Integer pageCount,
        LocalDate publishDate,
        @NotBlank UUID publisherId,
        @NotBlank UUID languageId)
        implements Command<CreatedBookResponse> {

}
