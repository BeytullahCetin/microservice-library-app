package com.turkcell.book_service.application.language.query;

import com.turkcell.book_service.application.language.dto.LanguageResponse;
import com.turkcell.book_service.core.cqrs.Query;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record FindByIdLanguageQuery(@NotBlank UUID id) implements Query<LanguageResponse> {
}
