package com.turkcell.book_service.application.book.mapper;

import org.springframework.stereotype.Component;

import com.turkcell.book_service.application.book.dto.BookCopyAvailabilityResponse;
import com.turkcell.book_service.domain.bookcopy.model.BookCopy;

@Component
public class BookCopyAvailabilityMapper {
    public BookCopyAvailabilityResponse toResponse(BookCopy domain, boolean availability) {
        return new BookCopyAvailabilityResponse(
                domain.getBookId().value(),
                availability,
                domain.getId().value());
    }
}
