package com.turkcell.borrow_service.mapper;

import com.turkcell.borrow_service.dto.request.CreateReservationRequest;
import com.turkcell.borrow_service.dto.response.ReservationResponse;
import com.turkcell.borrow_service.entity.Reservation;
import com.turkcell.borrow_service.entity.enums.ReservationStatus;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapper {

	public Reservation toReservation(CreateReservationRequest request) {
		return Reservation.builder()
				.customerId(request.customerId())
				.bookId(request.bookId())
				.expireAt(request.expireAt())
				.status(ReservationStatus.ACTIVE)
				.build();
	}

	public ReservationResponse toResponse(Reservation reservation) {
		return new ReservationResponse(
				reservation.getId(),
				reservation.getCustomerId(),
				reservation.getBookId(),
				reservation.getExpireAt(),
				reservation.getStatus());
	}
}

