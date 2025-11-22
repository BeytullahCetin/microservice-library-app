package com.turkcell.book_service.interfaces.web;

import com.turkcell.book_service.application.book.command.CreateBookCommand;
import com.turkcell.book_service.application.book.dto.BookCopyAvailabilityResponse;
import com.turkcell.book_service.application.book.dto.BookResponse;
import com.turkcell.book_service.application.book.dto.CreatedBookResponse;
import com.turkcell.book_service.application.book.query.CheckBookAvailabilityQuery;
import com.turkcell.book_service.application.book.query.FindByIdBookQuery;
import com.turkcell.book_service.application.book.query.ListBooksPagedQuery;
import com.turkcell.book_service.core.cqrs.CommandHandler;
import com.turkcell.book_service.core.cqrs.QueryHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
@RequiredArgsConstructor
public class BookController {
    private final QueryHandler<ListBooksPagedQuery, List<BookResponse>> listBookQueryHandler;
    private final QueryHandler<FindByIdBookQuery, BookResponse> findByIdBookQueryHandler;
    private final CommandHandler<CreateBookCommand, CreatedBookResponse> createBookQueryHandler;
    private final QueryHandler<CheckBookAvailabilityQuery, BookCopyAvailabilityResponse> checkBookAvailabilityQueryHandler;

    @GetMapping("/v1/books")
    public List<BookResponse> getBooksPaged(@Valid ListBooksPagedQuery query) {
        return listBookQueryHandler.handle(query);
    }

    @GetMapping("/v1/books/{id}")
    public BookResponse getBooksPaged(@Valid FindByIdBookQuery query) {
        return findByIdBookQueryHandler.handle(query);
    }

    @PostMapping("/v1/books")
    public CreatedBookResponse createBook(@RequestBody CreateBookCommand command) {
        return createBookQueryHandler.handle(command);
    }

    @GetMapping("/v1/books/available-to-borrow")
    public BookCopyAvailabilityResponse isBookCopyAvailableToBorrow(@Valid CheckBookAvailabilityQuery query) {
        return checkBookAvailabilityQueryHandler.handle(query);
    }
}
