package com.turkcell.borrow_service.client;

import com.turkcell.borrow_service.client.dto.BookCopyClientResponse;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "book-service", contextId = "bookCopyClient", path = "/api/v1/book-copies")
public interface BookCopyClient {

	@GetMapping("/{bookCopyId}")
	BookCopyClientResponse getBookCopy(@PathVariable("bookCopyId") UUID bookCopyId);
}

