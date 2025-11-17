package com.turkcell.borrow_service.client;

import com.turkcell.borrow_service.client.dto.CustomerClientResponse;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service", contextId = "customerClient", path = "/api/v1/customers")
public interface CustomerClient {

	@GetMapping("/{customerId}")
	CustomerClientResponse getCustomer(@PathVariable("customerId") UUID customerId);
}

