package com.turkcell.book_service.application.author.mapper;

import org.springframework.stereotype.Component;

import com.turkcell.book_service.application.author.dto.DeletedAuthorResponse;
import com.turkcell.book_service.domain.author.model.Author;

@Component
public class DeleteAuthorMapper {

	public DeletedAuthorResponse toResponse(Author domain) {
		return new DeletedAuthorResponse(domain.getId().value(), domain.getName());
	}
}
