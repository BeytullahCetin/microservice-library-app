package com.turkcell.book_service.application.bookcopy.mapper;

import org.springframework.stereotype.Component;

import com.turkcell.book_service.application.bookcopy.dto.BorrowedBookCopyResponse;
import com.turkcell.book_service.domain.bookcopy.model.BookCopy;

@Component
public class BorrowedBookCopyResponseMapper {
	public BorrowedBookCopyResponse toResponse(BookCopy domain) {
		return new BorrowedBookCopyResponse(
				domain.getId().value(),
				domain.getStatus());
	}
}
