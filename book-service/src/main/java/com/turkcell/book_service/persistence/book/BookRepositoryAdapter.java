package com.turkcell.book_service.persistence.book;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.turkcell.book_service.domain.book.model.Book;
import com.turkcell.book_service.domain.book.model.BookId;
import com.turkcell.book_service.domain.book.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BookRepositoryAdapter implements BookRepository {

	private final BookJpaRepository repository;
	private final BookEntityMapper bookEntityMapper;

	@Override
	public Optional<Book> findById(BookId id) {
		return repository
				.findById(Objects.requireNonNull(id.value()))
				.map(bookEntityMapper::toDomain);
	}

	@Override
	public List<Book> findAllPaged(Integer pageIndex, Integer pageSize) {
		return repository
				.findAll(PageRequest.of(pageIndex, pageSize))
				.stream()
				.map(bookEntityMapper::toDomain)
				.toList();
	}

	@Override
	public Book save(Book book) {
		BookJpaEntity entity = bookEntityMapper.toEntity(book);
		entity = repository.save(Objects.requireNonNull(entity));
		return bookEntityMapper.toDomain(entity);
	}

	@Override
	public void delete(BookId id) {
		repository.deleteById(Objects.requireNonNull(id.value()));
	}
}
