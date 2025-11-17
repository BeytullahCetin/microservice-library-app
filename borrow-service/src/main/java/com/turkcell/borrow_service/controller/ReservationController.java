package com.turkcell.borrow_service.controller;

import com.turkcell.borrow_service.dto.request.CreateReservationRequest;
import com.turkcell.borrow_service.dto.request.UpdateReservationStatusRequest;
import com.turkcell.borrow_service.dto.response.ReservationResponse;
import com.turkcell.borrow_service.service.ReservationService;
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
@RequestMapping("/api/v1/reservations")
@RequiredArgsConstructor
public class ReservationController {

	private final ReservationService reservationService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ReservationResponse createReservation(@Valid @RequestBody CreateReservationRequest request) {
		return reservationService.createReservation(request);
	}

	@GetMapping("/{reservationId}")
	public ReservationResponse getReservation(@PathVariable UUID reservationId) {
		return reservationService.getReservation(reservationId);
	}

	@GetMapping("/customers/{customerId}")
	public List<ReservationResponse> getCustomerReservations(@PathVariable UUID customerId) {
		return reservationService.getCustomerReservations(customerId);
	}

	@GetMapping("/books/{bookId}/active")
	public List<ReservationResponse> getActiveReservationsForBook(@PathVariable UUID bookId) {
		return reservationService.getActiveReservationsForBook(bookId);
	}

	@PatchMapping("/{reservationId}/status")
	public ReservationResponse updateReservationStatus(@PathVariable UUID reservationId,
			@Valid @RequestBody UpdateReservationStatusRequest request) {
		return reservationService.updateStatus(reservationId, request);
	}
}

