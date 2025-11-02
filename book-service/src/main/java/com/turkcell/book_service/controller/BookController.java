package com.turkcell.book_service.controller;

import com.turkcell.book_service.application.dto.BookResponse;
import com.turkcell.book_service.application.dto.CreateBookRequest;
import com.turkcell.book_service.application.dto.UpdateBookRequest;
import com.turkcell.book_service.application.service.BookApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {
	private final BookApplicationService bookService;

	public BookController(BookApplicationService bookService) {
		this.bookService = bookService;
	}

	@GetMapping
	public List<BookResponse> getBooks() {
		return bookService.list();
	}

	@GetMapping("{id}")
	public BookResponse getBookById(@PathVariable Long id) {
		return bookService.getById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public BookResponse createBook(@Valid @RequestBody CreateBookRequest request) {
		return bookService.create(request);
	}

	@PutMapping("{id}")
	public BookResponse updateBook(@PathVariable Long id, @Valid @RequestBody UpdateBookRequest request) {
		return bookService.update(id, request);
	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteBook(@PathVariable Long id) {
		bookService.delete(id);
	}
}
