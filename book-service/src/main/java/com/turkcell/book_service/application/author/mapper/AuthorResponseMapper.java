package com.turkcell.book_service.application.author.mapper;

import org.springframework.stereotype.Component;

import com.turkcell.book_service.application.author.dto.AuthorResponse;
import com.turkcell.book_service.domain.author.model.Author;

@Component
public class AuthorResponseMapper {
	public AuthorResponse toResponse(Author domain) {
		return new AuthorResponse(
				domain.getId().value(),
				domain.getName());
	}
}
