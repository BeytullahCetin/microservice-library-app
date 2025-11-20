package com.turkcell.book_service.application.book.mapper;

import org.springframework.stereotype.Component;

import com.turkcell.book_service.application.book.dto.BookResponse;
import com.turkcell.book_service.domain.book.model.Book;

@Component
public class BookResponseMapper {
    public BookResponse toResponse(Book domain) {
        return new BookResponse(
                domain.getId().value(),
                domain.getTitle(),
                domain.getIsbn().value(),
                domain.getPageCount(),
                domain.getPublishDate());
    }
}
