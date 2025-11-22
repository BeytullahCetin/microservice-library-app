package com.turkcell.borrow_service.client.dto;

import java.util.UUID;

public record BookCopyAvailabilityResponse(UUID id, boolean availableToBorrow) {

}
