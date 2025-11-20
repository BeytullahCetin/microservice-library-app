package com.turkcell.book_service.application.bookcopy.command;

import org.springframework.stereotype.Component;

import com.turkcell.book_service.application.bookcopy.dto.CreatedBookCopyResponse;
import com.turkcell.book_service.application.bookcopy.mapper.CreateBookCopyMapper;
import com.turkcell.book_service.core.cqrs.CommandHandler;
import com.turkcell.book_service.domain.bookcopy.model.BookCopy;
import com.turkcell.book_service.domain.bookcopy.repository.BookCopyRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreateBookCopyCommandHandler implements CommandHandler<CreateBookCopyCommand, CreatedBookCopyResponse> {

	private final BookCopyRepository bookCopyRepository;
	private final CreateBookCopyMapper createBookCopyMapper;

	@Override
	public CreatedBookCopyResponse handle(CreateBookCopyCommand command) {
		BookCopy bookCopy = createBookCopyMapper.toDomain(command);
		bookCopy = bookCopyRepository.save(bookCopy);
		return createBookCopyMapper.toResponse(bookCopy);
	}
}
