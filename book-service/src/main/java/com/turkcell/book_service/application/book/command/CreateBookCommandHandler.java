package com.turkcell.book_service.application.book.command;

import com.turkcell.book_service.application.book.dto.CreatedBookResponse;
import com.turkcell.book_service.application.book.mapper.CreateBookMapper;
import com.turkcell.book_service.core.cqrs.CommandHandler;
import com.turkcell.book_service.domain.book.model.Book;
import com.turkcell.book_service.domain.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateBookCommandHandler implements CommandHandler<CreateBookCommand, CreatedBookResponse> {

    private final BookRepository bookRepository;
    private final CreateBookMapper createBookMapper;

    @Override
    public CreatedBookResponse handle(CreateBookCommand command) {

        // TODO: Check if publisher exist
        // TODO: Check if language exist

        Book book = createBookMapper.toDomain(command);
        book = bookRepository.save(book);
        return createBookMapper.toResponse(book);
    }
}
