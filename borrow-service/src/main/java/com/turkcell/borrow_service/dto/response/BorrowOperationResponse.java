package com.turkcell.borrow_service.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record BorrowOperationResponse(
		ResultType result,
		BorrowResponse borrow,
		ReservationResponse reservation,
		String message) {

	public static BorrowOperationResponse borrowCreated(BorrowResponse borrow) {
		return new BorrowOperationResponse(ResultType.BORROW_CREATED, borrow, null, "Borrow created successfully");
	}

	public static BorrowOperationResponse reservationCreated(ReservationResponse reservation, String message) {
		return new BorrowOperationResponse(ResultType.RESERVATION_CREATED, null, reservation, message);
	}

	public enum ResultType {
		BORROW_CREATED,
		RESERVATION_CREATED
	}
}

