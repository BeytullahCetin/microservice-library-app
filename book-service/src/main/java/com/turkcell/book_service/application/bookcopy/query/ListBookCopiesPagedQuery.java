package com.turkcell.book_service.application.bookcopy.query;

import java.util.List;

import com.turkcell.book_service.application.bookcopy.dto.BookCopyResponse;
import com.turkcell.book_service.core.cqrs.Query;

import jakarta.validation.constraints.Min;

public record ListBookCopiesPagedQuery(
		@Min(0) Integer pageIndex,
		@Min(1) Integer pageSize)
		implements Query<List<BookCopyResponse>> {

}
