package com.turkcell.book_service.application.book.dto;

import java.time.LocalDate;
import java.util.UUID;

public record CreatedBookResponse(UUID id, String title, String isbn, int pageCount, LocalDate publishDate) {

}
