package com.turkcell.book_service.application.book.query;

import org.springframework.stereotype.Component;

import com.turkcell.book_service.application.book.dto.BookCopyAvailabilityResponse;
import com.turkcell.book_service.application.book.mapper.BookCopyAvailabilityMapper;
import com.turkcell.book_service.core.cqrs.QueryHandler;
import com.turkcell.book_service.domain.book.model.Book;
import com.turkcell.book_service.domain.book.model.BookId;
import com.turkcell.book_service.domain.book.repository.BookRepository;
import com.turkcell.book_service.domain.bookcopy.model.BookCopy;
import com.turkcell.book_service.domain.bookcopy.repository.BookCopyRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CheckBookAvailabilityQueryHandler implements QueryHandler<CheckBookAvailabilityQuery, BookCopyAvailabilityResponse> {

	private final BookRepository bookRepository;
	private final BookCopyRepository bookCopyRepository;
	private final BookCopyAvailabilityMapper bookCopyAvailabilityMapper;

	@Override
	public BookCopyAvailabilityResponse handle(CheckBookAvailabilityQuery query) {

		Book book = bookRepository.findById(new BookId(query.id())).get();
		if (book == null) {
			throw new IllegalArgumentException("Book not found for id: " + query.id());
		}

		// Check if there is at least one available copy
		BookCopy availableCopy = bookCopyRepository.findFirstAvailableByBookId(book.getId())
				.orElseThrow(() -> new RuntimeException("Book copy not found for book id: " + query.id()));

		return bookCopyAvailabilityMapper.toResponse(availableCopy, availableCopy != null);
	}
}
