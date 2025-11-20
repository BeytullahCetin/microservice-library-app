package com.turkcell.book_service.application.translator.query;

import java.util.UUID;

import com.turkcell.book_service.application.translator.dto.TranslatorResponse;
import com.turkcell.book_service.core.cqrs.Query;

import jakarta.validation.constraints.NotBlank;

public record FindByIdTranslatorQuery(@NotBlank UUID id) implements Query<TranslatorResponse> {

}
