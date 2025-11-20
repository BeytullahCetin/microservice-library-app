package com.turkcell.book_service.application.translator.query;

import java.util.List;

import com.turkcell.book_service.application.translator.dto.TranslatorResponse;
import com.turkcell.book_service.core.cqrs.Query;

import jakarta.validation.constraints.Min;

public record ListTranslatorsPagedQuery(
		@Min(0) Integer pageIndex,
		@Min(1) Integer pageSize) implements Query<List<TranslatorResponse>> {
}
