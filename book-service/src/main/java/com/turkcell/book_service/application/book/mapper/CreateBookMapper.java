package com.turkcell.book_service.application.book.mapper;

import com.turkcell.book_service.application.book.command.CreateBookCommand;
import com.turkcell.book_service.application.book.dto.CreatedBookResponse;
import com.turkcell.book_service.domain.book.model.Book;
import com.turkcell.book_service.domain.book.model.ISBN;
import com.turkcell.book_service.domain.language.model.LanguageId;
import com.turkcell.book_service.domain.publisher.model.PublisherId;

import org.springframework.stereotype.Component;

@Component
public class CreateBookMapper {
    public Book toDomain(CreateBookCommand command) {
        return Book.create(
                command.title(),
                new ISBN(command.isbn()),
                command.pageCount(),
                command.publishDate(),
                new PublisherId(command.publisherId()),
                new LanguageId(command.languageId()));
    }

    public CreatedBookResponse toResponse(Book domain) {
        return new CreatedBookResponse(
                domain.getId().value(),
                domain.getIsbn().value(),
                domain.getTitle(),
                domain.getPageCount(),
                domain.getPublishDate());
    }
}