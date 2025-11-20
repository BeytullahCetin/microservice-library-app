package com.turkcell.book_service.application.author.dto;

import java.util.UUID;

public record CreatedAuthorResponse(UUID id, String name) {
}
