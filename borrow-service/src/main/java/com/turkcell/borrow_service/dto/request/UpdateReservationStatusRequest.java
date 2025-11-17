package com.turkcell.borrow_service.dto.request;

import com.turkcell.borrow_service.entity.enums.ReservationStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateReservationStatusRequest(@NotNull ReservationStatus status) {
}

