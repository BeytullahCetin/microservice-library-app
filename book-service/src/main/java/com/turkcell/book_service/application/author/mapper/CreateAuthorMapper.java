package com.turkcell.book_service.application.author.mapper;

import org.springframework.stereotype.Component;

import com.turkcell.book_service.application.author.command.CreateAuthorCommand;
import com.turkcell.book_service.application.author.dto.CreatedAuthorResponse;
import com.turkcell.book_service.domain.author.model.Author;

@Component
public class CreateAuthorMapper {
	public Author toDomain(CreateAuthorCommand command) {
		return Author.create(command.name());
	}

	public CreatedAuthorResponse toResponse(Author domain) {
		return new CreatedAuthorResponse(
				domain.getId().value(),
				domain.getName());
	}
}
