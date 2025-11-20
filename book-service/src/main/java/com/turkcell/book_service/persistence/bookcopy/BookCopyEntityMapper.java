package com.turkcell.book_service.persistence.bookcopy;

import org.springframework.stereotype.Component;

import com.turkcell.book_service.domain.book.model.BookId;
import com.turkcell.book_service.domain.bookcopy.model.BookCopy;
import com.turkcell.book_service.domain.bookcopy.model.BookCopyId;

@Component
public class BookCopyEntityMapper {
	public BookCopyJpaEntity toEntity(BookCopy domain) {
		BookCopyJpaEntity entity = new BookCopyJpaEntity();
		entity.setId(domain.getId().value());
		entity.setBookId(domain.getBookId().value());
		entity.setAcquisitionDate(domain.getAcquisitionDate());
		entity.setStatus(domain.getStatus());

		return entity;
	}

	public BookCopy toDomain(BookCopyJpaEntity entity) {
		return BookCopy.rehydrate(
				new BookCopyId(entity.getId()),
				new BookId(entity.getBookId()),
				entity.getAcquisitionDate(),
				entity.getStatus());
	}
}
