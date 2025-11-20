package com.turkcell.book_service.application.book.query;

import com.turkcell.book_service.application.book.dto.BookResponse;
import com.turkcell.book_service.core.cqrs.Query;
import jakarta.validation.constraints.Min;

import java.util.List;

public record ListBooksPagedQuery(
                @Min(0) Integer pageIndex,
                @Min(1) Integer pageSize)
                implements Query<List<BookResponse>> {

}
