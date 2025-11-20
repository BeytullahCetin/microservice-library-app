package com.turkcell.book_service.domain.author.repository;

import java.util.List;
import java.util.Optional;

import com.turkcell.book_service.domain.author.model.Author;
import com.turkcell.book_service.domain.author.model.AuthorId;

public interface AuthorRepository {
	Optional<Author> findById(AuthorId id);

	List<Author> findAllPaged(Integer pageIndex, Integer pageSize);

	Author save(Author author);

	void delete(AuthorId id);
}