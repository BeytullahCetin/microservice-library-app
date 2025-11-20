package com.turkcell.book_service.application.publisher.dto;

import java.util.UUID;

public record PublisherResponse(UUID id, String name, String address) {
}
