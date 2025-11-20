package com.turkcell.book_service.persistence.book;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.turkcell.book_service.domain.author.model.AuthorId;
import com.turkcell.book_service.domain.book.model.Book;
import com.turkcell.book_service.domain.book.model.BookId;
import com.turkcell.book_service.domain.book.model.ISBN;
import com.turkcell.book_service.domain.language.model.LanguageId;
import com.turkcell.book_service.domain.publisher.model.PublisherId;
import com.turkcell.book_service.domain.translator.model.TranslatorId;

@Component
public class BookEntityMapper {
	public BookJpaEntity toEntity(Book book) {
		BookJpaEntity entity = new BookJpaEntity();
		entity.setId(book.getId().value());
		entity.setTitle(book.getTitle());
		entity.setIsbn(book.getIsbn().value());
		entity.setPageCount(book.getPageCount());
		entity.setPublishDate(book.getPublishDate());
		entity.setPublisherId(book.getPublisherId().value());
		entity.setLanguageId(book.getLanguageId().value());

		entity.setAuthorIds(
				book.getAuthorIds().stream()
						.map(AuthorId::value)
						.collect(Collectors.toList()));

		entity.setTranslatorIds(
				book.getTranslatorIds().stream()
						.map(TranslatorId::value)
						.collect(Collectors.toList()));

		return entity;
	}

	public Book toDomain(BookJpaEntity entity) {
		return Book.rehydrate(
				new BookId(entity.getId()),
				entity.getTitle(),
				new ISBN(entity.getIsbn()),
				entity.getPageCount(),
				entity.getPublishDate(),
				new PublisherId(entity.getPublisherId()),
				new LanguageId(entity.getLanguageId()),
				entity.getAuthorIds().stream()
						.map(id -> new AuthorId(id))
						.collect(Collectors.toList()),
				entity.getTranslatorIds().stream()
						.map(id -> new TranslatorId(id))
						.collect(Collectors.toList()));
	}
}
