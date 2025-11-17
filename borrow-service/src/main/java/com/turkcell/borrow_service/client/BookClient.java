package com.turkcell.borrow_service.client;

import com.turkcell.borrow_service.client.dto.BookClientResponse;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "book-service", contextId = "bookClient", path = "/api/v1/books")
public interface BookClient {

	@GetMapping("/{bookId}")
	BookClientResponse getBook(@PathVariable("bookId") UUID bookId);
}

