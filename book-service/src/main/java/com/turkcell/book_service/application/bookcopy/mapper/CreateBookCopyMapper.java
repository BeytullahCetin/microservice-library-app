package com.turkcell.book_service.application.bookcopy.mapper;

import org.springframework.stereotype.Component;

import com.turkcell.book_service.application.bookcopy.command.CreateBookCopyCommand;
import com.turkcell.book_service.application.bookcopy.dto.CreatedBookCopyResponse;
import com.turkcell.book_service.domain.book.model.BookId;
import com.turkcell.book_service.domain.bookcopy.model.BookCopy;

@Component
public class CreateBookCopyMapper {
	public BookCopy toDomain(CreateBookCopyCommand command) {
		return BookCopy.create(
				new BookId(command.bookId()),
				command.acquisitionDate(),
				command.bookStatus());
	}

	public CreatedBookCopyResponse toResponse(BookCopy domain) {
		return new CreatedBookCopyResponse(
				domain.getId().value(),
				domain.getAcquisitionDate(),
				domain.getStatus(),
				domain.getBookId().value());
	}
}
