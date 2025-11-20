package com.turkcell.book_service.application.language.query;

import com.turkcell.book_service.application.language.dto.LanguageResponse;
import com.turkcell.book_service.core.cqrs.Query;
import jakarta.validation.constraints.Min;

import java.util.List;

public record ListLanguagesPagedQuery(@Min(0) Integer pageIndex,
                @Min(1) Integer pageSize)
                implements Query<List<LanguageResponse>> {
}
