package com.turkcell.book_service.application.bookcopy.query;

import java.util.List;

import org.springframework.stereotype.Component;

import com.turkcell.book_service.application.bookcopy.dto.BookCopyResponse;
import com.turkcell.book_service.application.bookcopy.mapper.BookCopyResponseMapper;
import com.turkcell.book_service.core.cqrs.QueryHandler;
import com.turkcell.book_service.domain.bookcopy.repository.BookCopyRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ListBookCopiesPagedQueryHandler
		implements QueryHandler<ListBookCopiesPagedQuery, List<BookCopyResponse>> {
	private final BookCopyRepository bookCopyRepository;
	private final BookCopyResponseMapper bookCopyResponseMapper;

	@Override
	public List<BookCopyResponse> handle(ListBookCopiesPagedQuery query) {
		return bookCopyRepository
				.findAllPaged(query.pageIndex(), query.pageSize())
				.stream()
				.map(bookCopyResponseMapper::toResponse)
				.toList();
	}

}
