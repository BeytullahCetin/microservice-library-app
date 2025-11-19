package com.turkcell.fine_service.client;

import com.turkcell.fine_service.client.dto.BorrowClientResponse;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "borrow-service", contextId = "fineBorrowClient", path = "/api/v1/borrows")
public interface BorrowClient {

	@GetMapping("/{borrowId}")
	BorrowClientResponse getBorrow(@PathVariable("borrowId") UUID borrowId);
}

