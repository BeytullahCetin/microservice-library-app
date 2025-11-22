package com.turkcell.borrow_service.client;

import com.turkcell.borrow_service.client.dto.BookCopyAvailabilityResponse;
import com.turkcell.borrow_service.client.dto.BookCopyClientResponse;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "book-service", contextId = "bookCopyClient", path = "/api/v1/book-copies")
public interface BookCopyClient {

	@GetMapping("/{bookCopyId}")
	BookCopyClientResponse getBookCopy(@PathVariable("bookCopyId") UUID bookCopyId);

	@GetMapping("/{bookCopyId}/available-to-borrow")
	BookCopyAvailabilityResponse isBookCopyAvailableToBorrow(@PathVariable("bookCopyId") UUID bookCopyId);

	@GetMapping("/books/{bookId}/available")
	BookCopyClientResponse getAvailableBookCopy(@PathVariable("bookId") UUID bookId);

	@PostMapping("/{bookCopyId}/borrow")
	void markBookCopyAsBorrowed(@PathVariable("bookCopyId") UUID bookCopyId);

	@PostMapping("/{bookCopyId}/return")
	void markBookCopyAsReturned(@PathVariable("bookCopyId") UUID bookCopyId);

}

