package com.turkcell.book_service.application.book.command;

import com.turkcell.book_service.application.book.dto.DeletedBookResponse;
import com.turkcell.book_service.application.book.mapper.DeleteBookMapper;
import com.turkcell.book_service.core.cqrs.CommandHandler;
import com.turkcell.book_service.domain.book.model.Book;
import com.turkcell.book_service.domain.book.model.BookId;
import com.turkcell.book_service.domain.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DeleteBookCommandHandler implements CommandHandler<DeleteBookCommand, DeletedBookResponse> {

    private final BookRepository bookRepository;
    private final DeleteBookMapper deleteBookMapper;

    @Override
    public DeletedBookResponse handle(DeleteBookCommand command) {
        Optional<Book> book = bookRepository.findById(new BookId(command.id()));

        if (book == null) {
            throw new IllegalArgumentException("Bu id ile bir book bulunamadı!");
        }

        bookRepository.delete(book.get().getId());
        return deleteBookMapper.toResponse(book.get());
    }
}