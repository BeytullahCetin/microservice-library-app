package com.turkcell.borrow_service.client;

import com.turkcell.borrow_service.client.dto.BookCopyClientResponse;
import com.turkcell.borrow_service.client.dto.BorrowBookCopyRequest;
import com.turkcell.borrow_service.client.dto.BorrowedBookCopyResponse;
import com.turkcell.borrow_service.client.dto.ReturnBookCopyRequest;
import com.turkcell.borrow_service.client.dto.ReturnedBookCopyResponse;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "book-service", contextId = "bookCopyClient", path = "/api/v1/book-copies")
public interface BookCopyClient {

	@GetMapping("/{bookCopyId}")
	BookCopyClientResponse getBookCopy(@PathVariable("bookCopyId") UUID bookCopyId);

	@PostMapping("/borrow")
	BorrowedBookCopyResponse borrowBookCopy(@RequestBody BorrowBookCopyRequest request);

	@PostMapping("/return")
	ReturnedBookCopyResponse returnBookCopy(@RequestBody ReturnBookCopyRequest request);

}

