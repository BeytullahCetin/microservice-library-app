package com.turkcell.fine_service.controller;

import com.turkcell.fine_service.dto.request.CreateFineRequest;
import com.turkcell.fine_service.dto.request.MarkFineAsPaidRequest;
import com.turkcell.fine_service.dto.response.FineResponse;
import com.turkcell.fine_service.service.FineService;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/fines")
@RequiredArgsConstructor
public class FineController {

	private final FineService fineService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public FineResponse createFine(@Valid @RequestBody CreateFineRequest request) {
		return fineService.createFine(request);
	}

	@GetMapping("/{fineId}")
	public FineResponse getFine(@PathVariable UUID fineId) {
		return fineService.getFine(fineId);
	}

	@GetMapping("/borrow/{borrowId}")
	public List<FineResponse> getFinesForBorrow(@PathVariable UUID borrowId) {
		return fineService.getFinesForBorrow(borrowId);
	}

	@GetMapping("/outstanding")
	public List<FineResponse> getOutstandingFines() {
		return fineService.getOutstandingFines();
	}

	@PatchMapping("/{fineId}/payment")
	public FineResponse markFineAsPaid(@PathVariable UUID fineId,
			@Valid @RequestBody MarkFineAsPaidRequest request) {
		return fineService.markFineAsPaid(fineId, request);
	}
}

