package com.turkcell.book_service.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {
	@GetMapping
	public String getBooks() {
		return "get Books";
	}

	@GetMapping("{id}")
	public String getBookById(@PathVariable String id) {
		return "Book " + id;
	}

	@PostMapping
	public String createBook() {
		return "Book created successfully";
	}

	@PutMapping
	public String updateBook() {
		return "Book updated successfully";
	}

	@DeleteMapping
	public String deleteBook() {
		return "Book deleted successfully";
	}
}
