package com.turkcell.book_service.application.bookcopy.command;

import org.springframework.stereotype.Component;

import com.turkcell.book_service.application.bookcopy.dto.ReturnedBookCopyResponse;
import com.turkcell.book_service.application.bookcopy.mapper.ReturnedBookCopyResponseMapper;
import com.turkcell.book_service.core.cqrs.CommandHandler;
import com.turkcell.book_service.domain.bookcopy.model.BookCopy;
import com.turkcell.book_service.domain.bookcopy.model.BookCopyId;
import com.turkcell.book_service.domain.bookcopy.repository.BookCopyRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ReturnBookCopyCommandHandler implements CommandHandler<ReturnBookCopyCommand, ReturnedBookCopyResponse> {

	private final BookCopyRepository bookCopyRepository;
	private final ReturnedBookCopyResponseMapper returnedBookCopyResponseMapper;

	@Override
	public ReturnedBookCopyResponse handle(ReturnBookCopyCommand command) {

		BookCopy bookCopy = bookCopyRepository.findById(new BookCopyId(command.id()))
				.orElseThrow(() -> new IllegalStateException(
						"No available book copy found for book ID: " + command.id()));

		bookCopy.changeStatus("Available");
		bookCopy = bookCopyRepository.save(bookCopy);
		return returnedBookCopyResponseMapper.toResponse(bookCopy);
	}
}
