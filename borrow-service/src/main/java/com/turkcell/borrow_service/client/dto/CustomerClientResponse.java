package com.turkcell.borrow_service.client.dto;

import java.util.UUID;

public record CustomerClientResponse(UUID id, String firstName, String lastName) {
}

