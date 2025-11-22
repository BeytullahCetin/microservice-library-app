package com.turkcell.book_service.interfaces.web;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.book_service.application.bookcopy.command.BorrowBookCopyCommand;
import com.turkcell.book_service.application.bookcopy.command.CreateBookCopyCommand;
import com.turkcell.book_service.application.bookcopy.command.ReturnBookCopyCommand;
import com.turkcell.book_service.application.bookcopy.dto.BookCopyResponse;
import com.turkcell.book_service.application.bookcopy.dto.BorrowedBookCopyResponse;
import com.turkcell.book_service.application.bookcopy.dto.CreatedBookCopyResponse;
import com.turkcell.book_service.application.bookcopy.dto.ReturnedBookCopyResponse;
import com.turkcell.book_service.application.bookcopy.query.FindByIdBookCopyQuery;
import com.turkcell.book_service.application.bookcopy.query.ListBookCopiesPagedQuery;
import com.turkcell.book_service.core.cqrs.CommandHandler;
import com.turkcell.book_service.core.cqrs.QueryHandler;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@Validated
@RequiredArgsConstructor
public class BookCopyController {
	private final QueryHandler<ListBookCopiesPagedQuery, List<BookCopyResponse>> listBookCopyQueryHandler;
	private final QueryHandler<FindByIdBookCopyQuery, BookCopyResponse> findByIdBookCopyQueryHandler;
	private final CommandHandler<CreateBookCopyCommand, CreatedBookCopyResponse> createBookCopyCommandHandler;
	private final CommandHandler<BorrowBookCopyCommand, BorrowedBookCopyResponse> borrowBookCopyCommandHandler;
	private final CommandHandler<ReturnBookCopyCommand, ReturnedBookCopyResponse> returnBookCopyCommandHandler;

	@GetMapping("/v1/book-copies")
	public List<BookCopyResponse> getBookCopiesPaged(@Valid ListBookCopiesPagedQuery query) {
		return listBookCopyQueryHandler.handle(query);
	}

	@GetMapping("/v1/book-copies/{id}")
	public BookCopyResponse getBookCopyById(@Valid FindByIdBookCopyQuery query) {
		return findByIdBookCopyQueryHandler.handle(query);
	}

	@PostMapping("/v1/book-copies")
	public CreatedBookCopyResponse createBookCopy(@RequestBody @Valid CreateBookCopyCommand command) {
		return createBookCopyCommandHandler.handle(command);
	}

	@PostMapping("/v1/book-copies/borrow")
	public BorrowedBookCopyResponse borrowBookCopy(@RequestBody @Valid BorrowBookCopyCommand command) {
		return borrowBookCopyCommandHandler.handle(command);
	}

	@PostMapping("/v1/book-copies/return")
	public ReturnedBookCopyResponse returnBookCopy(@RequestBody @Valid ReturnBookCopyCommand command) {
		return returnBookCopyCommandHandler.handle(command);
	}

}
