package com.turkcell.book_service.domain.bookcopy.repository;

import java.util.List;
import java.util.Optional;

import com.turkcell.book_service.domain.bookcopy.model.BookCopy;
import com.turkcell.book_service.domain.bookcopy.model.BookCopyId;

public interface BookCopyRepository {
	Optional<BookCopy> findById(BookCopyId id);

	List<BookCopy> findAllPaged(Integer pageIndex, Integer pageSize);

	BookCopy save(BookCopy bookCopy);

	void delete(BookCopyId id);
}
