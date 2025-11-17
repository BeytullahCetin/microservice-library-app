package com.turkcell.borrow_service.dto.response;

import com.turkcell.borrow_service.entity.enums.ReservationStatus;
import java.time.LocalDateTime;
import java.util.UUID;

public record ReservationResponse(
		UUID id,
		UUID customerId,
		UUID bookId,
		LocalDateTime expireAt,
		ReservationStatus status) {
}

