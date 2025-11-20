package com.turkcell.book_service.application.author.query;

import java.util.List;

import com.turkcell.book_service.application.author.dto.AuthorResponse;
import com.turkcell.book_service.core.cqrs.Query;

import jakarta.validation.constraints.Min;

public record ListAuthorsPagedQuery(
		@Min(0) Integer pageIndex,
		@Min(1) Integer pageSize)
		implements Query<List<AuthorResponse>> {

}
