package com.turkcell.book_service.application.book.query;

import java.util.UUID;

import com.turkcell.book_service.application.book.dto.BookCopyAvailabilityResponse;
import com.turkcell.book_service.core.cqrs.Query;

public record CheckBookAvailabilityQuery(UUID id) implements Query<BookCopyAvailabilityResponse> {

}
