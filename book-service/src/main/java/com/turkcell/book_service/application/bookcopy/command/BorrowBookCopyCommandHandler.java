package com.turkcell.book_service.application.bookcopy.command;

import org.springframework.stereotype.Component;

import com.turkcell.book_service.application.bookcopy.dto.BorrowedBookCopyResponse;
import com.turkcell.book_service.application.bookcopy.mapper.BorrowedBookCopyResponseMapper;
import com.turkcell.book_service.core.cqrs.CommandHandler;
import com.turkcell.book_service.domain.bookcopy.model.BookCopy;
import com.turkcell.book_service.domain.bookcopy.model.BookCopyId;
import com.turkcell.book_service.domain.bookcopy.repository.BookCopyRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BorrowBookCopyCommandHandler implements CommandHandler<BorrowBookCopyCommand, BorrowedBookCopyResponse> {

	private final BookCopyRepository bookCopyRepository;
	private final BorrowedBookCopyResponseMapper borrowedBookCopyResponseMapper;

	@Override
	public BorrowedBookCopyResponse handle(BorrowBookCopyCommand command) {

		BookCopy bookCopy = bookCopyRepository.findById(new BookCopyId(command.id()))
				.orElseThrow(() -> new IllegalStateException(
						"No available book copy found for book ID: " + command.id()));

		bookCopy.changeStatus("Borrowed");
		bookCopy = bookCopyRepository.save(bookCopy);
		return borrowedBookCopyResponseMapper.toResponse(bookCopy);
	}
}
