package com.turkcell.book_service.domain.book.repository;

import java.util.List;
import java.util.Optional;

import com.turkcell.book_service.domain.book.model.Book;
import com.turkcell.book_service.domain.book.model.BookId;

public interface BookRepository {
	Optional<Book> findById(BookId id);

	List<Book> findAllPaged(Integer pageIndex, Integer pageSize);

	Book save(Book book);

	void delete(BookId id);
}
