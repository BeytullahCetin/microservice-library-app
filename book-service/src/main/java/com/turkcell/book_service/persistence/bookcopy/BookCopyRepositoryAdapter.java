package com.turkcell.book_service.persistence.bookcopy;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.turkcell.book_service.domain.book.model.BookId;
import com.turkcell.book_service.domain.bookcopy.model.BookCopy;
import com.turkcell.book_service.domain.bookcopy.model.BookCopyId;
import com.turkcell.book_service.domain.bookcopy.repository.BookCopyRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BookCopyRepositoryAdapter implements BookCopyRepository {
	private final BookCopyJpaRepository repository;
	private final BookCopyEntityMapper bookCopyEntityMapper;

	@Override
	public Optional<BookCopy> findById(BookCopyId id) {
		return repository
				.findById(Objects.requireNonNull(id.value()))
				.map(bookCopyEntityMapper::toDomain);
	}

	@Override
	public List<BookCopy> findAllPaged(Integer pageIndex, Integer pageSize) {
		return repository
				.findAll(PageRequest.of(pageIndex, pageSize))
				.stream()
				.map(bookCopyEntityMapper::toDomain)
				.toList();
	}

	@Override
	public BookCopy save(BookCopy bookCopy) {
		BookCopyJpaEntity entity = bookCopyEntityMapper.toEntity(bookCopy);
		entity = repository.save(Objects.requireNonNull(entity));
		return bookCopyEntityMapper.toDomain(entity);
	}

	@Override
	public void delete(BookCopyId id) {
		repository.deleteById(Objects.requireNonNull(id.value()));
	}

	@Override
	public Optional<BookCopy> findFirstAvailableByBookId(BookId bookId) {
		return repository
				.findAll()
				.stream()
				.map(bookCopyEntityMapper::toDomain)
				.filter(bookCopy -> bookCopy.getBookId().equals(bookId))
				.filter(bookCopy -> "AVAILABLE".equals(bookCopy.getStatus()))
				.findFirst();
	}
}
