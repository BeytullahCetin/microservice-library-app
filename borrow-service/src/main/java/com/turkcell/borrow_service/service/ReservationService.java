package com.turkcell.borrow_service.service;

import com.turkcell.borrow_service.dto.request.CreateReservationRequest;
import com.turkcell.borrow_service.dto.request.UpdateReservationStatusRequest;
import com.turkcell.borrow_service.dto.response.ReservationResponse;
import java.util.List;
import java.util.UUID;

public interface ReservationService {

	ReservationResponse createReservation(CreateReservationRequest request);

	ReservationResponse getReservation(UUID id);

	List<ReservationResponse> getCustomerReservations(UUID customerId);

	List<ReservationResponse> getActiveReservationsForBook(UUID bookId);

	ReservationResponse updateStatus(UUID reservationId, UpdateReservationStatusRequest request);

	int expireReservations();
}

