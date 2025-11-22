package com.turkcell.borrow_service.client.dto;

import java.util.UUID;

public record ReturnedBookCopyResponse(UUID id, String bookStatus) {
}

