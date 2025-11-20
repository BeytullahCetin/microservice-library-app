package com.turkcell.book_service.application.bookcopy.query;

import org.springframework.stereotype.Component;

import com.turkcell.book_service.application.bookcopy.dto.BookCopyResponse;
import com.turkcell.book_service.application.bookcopy.mapper.BookCopyResponseMapper;
import com.turkcell.book_service.core.cqrs.QueryHandler;
import com.turkcell.book_service.domain.bookcopy.model.BookCopyId;
import com.turkcell.book_service.domain.bookcopy.repository.BookCopyRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindByIdBookCopyQueryHandler implements QueryHandler<FindByIdBookCopyQuery, BookCopyResponse> {
	private final BookCopyRepository bookCopyRepository;
	private final BookCopyResponseMapper bookCopyResponseMapper;

	@Override
	public BookCopyResponse handle(FindByIdBookCopyQuery query) {
		return bookCopyRepository
				.findById(new BookCopyId(query.id()))
				.map(bookCopyResponseMapper::toResponse)
				.orElseThrow(() -> new RuntimeException("BookCopy not found with id: " + query.id()));
	}

}
