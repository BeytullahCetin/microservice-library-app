package com.turkcell.borrow_service.client.dto;

import java.util.UUID;

public record BorrowedBookCopyResponse(UUID id, String bookStatus) {
}

