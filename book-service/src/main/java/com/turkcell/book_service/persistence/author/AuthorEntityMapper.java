package com.turkcell.book_service.persistence.author;

import org.springframework.stereotype.Component;

import com.turkcell.book_service.domain.author.model.Author;
import com.turkcell.book_service.domain.author.model.AuthorId;

@Component
public class AuthorEntityMapper {
	public AuthorJpaEntity toEntity(Author domain) {
		AuthorJpaEntity entity = new AuthorJpaEntity();
		entity.setId(domain.getId().value());
		entity.setName(domain.getName());

		return entity;
	}

	public Author toDomain(AuthorJpaEntity entity) {
		return Author.rehydrate(new AuthorId(entity.getId()), entity.getName());
	}
}
