package com.turkcell.book_service.domain.book.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.turkcell.book_service.domain.author.model.AuthorId;
import com.turkcell.book_service.domain.language.model.LanguageId;
import com.turkcell.book_service.domain.publisher.model.PublisherId;
import com.turkcell.book_service.domain.translator.model.TranslatorId;

public class Book {
	private final BookId id;
	private String title;
	private ISBN isbn;
	private int pageCount;
	private LocalDate publishDate;

	private PublisherId publisherId;
	private LanguageId languageId;

	private List<AuthorId> authorIds;
	private List<TranslatorId> translatorIds;

	private Book(BookId id, String title, ISBN isbn, int pageCount,
			LocalDate publishDate, PublisherId publisherId, LanguageId languageId) {
		this.id = id;
		this.title = title;
		this.isbn = isbn;
		this.pageCount = pageCount;
		this.publishDate = publishDate;
		this.publisherId = publisherId;
		this.languageId = languageId;
		this.authorIds = new ArrayList<>();
		this.translatorIds = new ArrayList<>();
	}

	public static Book create(String title, ISBN isbn, int pageCount,
			LocalDate publishDate, PublisherId publisherId,
			LanguageId languageId) {
		validateTitle(title);
		validatePageCount(pageCount);
		validatePublishDate(publishDate);

		if (publisherId == null) {
			throw new IllegalArgumentException("Publisher cannot be null");
		}
		if (languageId == null) {
			throw new IllegalArgumentException("Language cannot be null");
		}

		Objects.requireNonNull(isbn, "ISBN cannot be null");

		return new Book(BookId.generate(), title, isbn, pageCount,
				publishDate, publisherId, languageId);
	}

	public static Book rehydrate(BookId id, String title, ISBN isbn, int pageCount,
			LocalDate publishDate, PublisherId publisherId,
			LanguageId languageId, List<AuthorId> authorIds,
			List<TranslatorId> translatorIds) {
		Book book = new Book(id, title, isbn, pageCount, publishDate,
				publisherId, languageId);
		book.authorIds = new ArrayList<>(authorIds);
		book.translatorIds = new ArrayList<>(translatorIds);
		return book;
	}

	public void updateTitle(String newTitle) {
		validateTitle(newTitle);
		this.title = newTitle;
	}

	public void updatePageCount(int newPageCount) {
		validatePageCount(newPageCount);
		this.pageCount = newPageCount;
	}

	public void changePublisher(PublisherId newPublisherId) {
		if (newPublisherId == null) {
			throw new IllegalArgumentException("Publisher cannot be null");
		}
		this.publisherId = newPublisherId;
	}

	public void changeLanguage(LanguageId newLanguageId) {
		if (newLanguageId == null) {
			throw new IllegalArgumentException("Language cannot be null");
		}
		this.languageId = newLanguageId;
	}

	public void addAuthor(AuthorId authorId) {
		if (authorId == null) {
			throw new IllegalArgumentException("Author ID cannot be null");
		}
		if (authorIds.contains(authorId)) {
			throw new IllegalArgumentException("Author already added to this book");
		}
		authorIds.add(authorId);
	}

	public void removeAuthor(AuthorId authorId) {
		if (!authorIds.remove(authorId)) {
			throw new IllegalArgumentException("Author not found in this book");
		}
	}

	public void addTranslator(TranslatorId translatorId) {
		if (translatorId == null) {
			throw new IllegalArgumentException("Translator ID cannot be null");
		}
		if (translatorIds.contains(translatorId)) {
			throw new IllegalArgumentException("Translator already added to this book");
		}
		translatorIds.add(translatorId);
	}

	public void removeTranslator(TranslatorId translatorId) {
		if (!translatorIds.remove(translatorId)) {
			throw new IllegalArgumentException("Translator not found in this book");
		}
	}

	private static void validateTitle(String title) {
		if (title == null || title.trim().isEmpty()) {
			throw new IllegalArgumentException("Title cannot be null or empty");
		}
		if (title.length() > 500) {
			throw new IllegalArgumentException("Title must be less than 500 characters");
		}
	}

	private static void validatePageCount(int pageCount) {
		if (pageCount <= 0) {
			throw new IllegalArgumentException("Page count must be positive");
		}
		if (pageCount > 100000) {
			throw new IllegalArgumentException("Page count seems unrealistic");
		}
	}

	private static void validatePublishDate(LocalDate publishDate) {
		if (publishDate == null) {
			throw new IllegalArgumentException("Publish date cannot be null");
		}
		if (publishDate.isAfter(LocalDate.now())) {
			throw new IllegalArgumentException("Publish date cannot be in the future");
		}
	}

	public BookId getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public ISBN getIsbn() {
		return isbn;
	}

	public int getPageCount() {
		return pageCount;
	}

	public LocalDate getPublishDate() {
		return publishDate;
	}

	public PublisherId getPublisherId() {
		return publisherId;
	}

	public LanguageId getLanguageId() {
		return languageId;
	}

	public List<AuthorId> getAuthorIds() {
		return authorIds;
	}

	public List<TranslatorId> getTranslatorIds() {
		return translatorIds;
	}
}