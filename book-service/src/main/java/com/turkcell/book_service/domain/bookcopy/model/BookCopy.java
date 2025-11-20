package com.turkcell.book_service.domain.bookcopy.model;

import java.time.LocalDateTime;

import com.turkcell.book_service.domain.book.model.BookId;

public class BookCopy {
	private final BookCopyId id;
	private final BookId bookId;

	private LocalDateTime acquisitionDate;
	private String status;

	private BookCopy(BookCopyId id, BookId bookId, LocalDateTime acquisitionDate, String status) {
		this.id = id;
		this.bookId = bookId;
		this.acquisitionDate = acquisitionDate;
		this.status = status;
	}

	public static BookCopy create(BookId bookId, LocalDateTime acquisitionDate, String status) {
		validateStatus(status);
		validateAcquisitionDate(acquisitionDate);

		if (bookId == null) {
			throw new IllegalArgumentException("Book cannot be null");
		}

		return new BookCopy(BookCopyId.generate(), bookId, acquisitionDate, status);
	}

	public static BookCopy rehydrate(BookCopyId id, BookId bookId, LocalDateTime acquisitionDate, String status) {
		BookCopy bookCopy = new BookCopy(id, bookId, acquisitionDate, status);
		return bookCopy;
	}

	public void changeAcquisitionDate(LocalDateTime acquisitionDate) {
		validateAcquisitionDate(acquisitionDate);
		this.acquisitionDate = acquisitionDate;
	}

	public void changeStatus(String status) {
		validateStatus(status);
		this.status = status;
	}

	private static void validateAcquisitionDate(LocalDateTime acquisitionDate) {
		if (acquisitionDate == null) {
			throw new IllegalArgumentException("Acquisition date cannot be null");
		}

		if (acquisitionDate.isAfter(LocalDateTime.now())) {
			throw new IllegalArgumentException("Acquisition date cannot be in the future");
		}
	}

	private static void validateStatus(String status) {
		if (status == null || status.trim().isEmpty()) {
			throw new IllegalArgumentException("Status cannot be null or empty");
		}
		if (status.length() > 500) {
			throw new IllegalArgumentException("Status must be less than 500 characters");
		}
	}

	public BookCopyId getId() {
		return id;
	}

	public BookId getBookId() {
		return bookId;
	}

	public LocalDateTime getAcquisitionDate() {
		return acquisitionDate;
	}

	public String getStatus() {
		return status;
	}
}
