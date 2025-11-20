package com.turkcell.book_service.application.book.mapper;

import com.turkcell.book_service.application.book.dto.DeletedBookResponse;
import com.turkcell.book_service.domain.book.model.Book;
import org.springframework.stereotype.Component;

@Component
public class DeleteBookMapper {

    public DeletedBookResponse toResponse(Book domain) {
        return new DeletedBookResponse(
                domain.getId().value(),
                domain.getTitle(),
                domain.getIsbn().value(),
                domain.getPageCount(),
                domain.getPublishDate());
    }
}
