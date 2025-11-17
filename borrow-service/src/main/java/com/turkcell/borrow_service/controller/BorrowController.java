package com.turkcell.borrow_service.controller;

import com.turkcell.borrow_service.dto.request.CompleteBorrowRequest;
import com.turkcell.borrow_service.dto.request.CreateBorrowRequest;
import com.turkcell.borrow_service.dto.response.BorrowResponse;
import com.turkcell.borrow_service.service.BorrowService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/borrows")
@RequiredArgsConstructor
public class BorrowController {

	private final BorrowService borrowService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public BorrowResponse createBorrow(@Valid @RequestBody CreateBorrowRequest request) {
		return borrowService.createBorrow(request);
	}

	@GetMapping("/{borrowId}")
	public BorrowResponse getBorrow(@PathVariable UUID borrowId) {
		return borrowService.getBorrow(borrowId);
	}

	@GetMapping
	public List<BorrowResponse> listBorrows(@RequestParam(name = "activeOnly", defaultValue = "false") boolean activeOnly) {
		return borrowService.getBorrows(activeOnly);
	}

	@PatchMapping("/{borrowId}/return")
	public BorrowResponse returnBorrow(@PathVariable UUID borrowId,
			@Valid @RequestBody CompleteBorrowRequest request) {
		return borrowService.completeBorrow(borrowId, request);
	}
}

