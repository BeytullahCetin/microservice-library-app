package com.turkcell.borrow_service.client.dto;

import java.util.UUID;

public record BookCopyClientResponse(UUID id, UUID bookId, String barcode, String status) {
}

