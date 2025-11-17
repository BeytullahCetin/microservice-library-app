package com.turkcell.borrow_service.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

public record CreateReservationRequest(
		@NotNull UUID customerId,
		@NotNull UUID bookId,
		@NotNull @Future LocalDateTime expireAt) {
}

