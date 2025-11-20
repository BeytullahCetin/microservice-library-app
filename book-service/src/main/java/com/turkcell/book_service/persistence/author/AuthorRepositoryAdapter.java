package com.turkcell.book_service.persistence.author;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.turkcell.book_service.domain.author.model.Author;
import com.turkcell.book_service.domain.author.model.AuthorId;
import com.turkcell.book_service.domain.author.repository.AuthorRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AuthorRepositoryAdapter implements AuthorRepository {

	private final AuthorJpaRepository repository;
	private final AuthorEntityMapper authorEntityMapper;

	@Override
	public Optional<Author> findById(AuthorId id) {
		return repository
				.findById(Objects.requireNonNull(id.value()))
				.map(authorEntityMapper::toDomain);
	}

	@Override
	public List<Author> findAllPaged(Integer pageIndex, Integer pageSize) {
		return repository
				.findAll(PageRequest.of(pageIndex, pageSize))
				.stream()
				.map(authorEntityMapper::toDomain)
				.toList();
	}

	@Override
	public Author save(Author author) {
		AuthorJpaEntity entity = authorEntityMapper.toEntity(author);
		entity = repository.save(Objects.requireNonNull(entity));
		return authorEntityMapper.toDomain(entity);
	}

	@Override
	public void delete(AuthorId id) {
		repository.deleteById(Objects.requireNonNull(id.value()));
	}

}