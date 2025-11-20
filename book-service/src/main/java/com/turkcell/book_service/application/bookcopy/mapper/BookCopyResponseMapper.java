package com.turkcell.book_service.application.bookcopy.mapper;

import org.springframework.stereotype.Component;

import com.turkcell.book_service.application.bookcopy.dto.BookCopyResponse;
import com.turkcell.book_service.domain.bookcopy.model.BookCopy;

@Component
public class BookCopyResponseMapper {
	public BookCopyResponse toResponse(BookCopy domain) {
		return new BookCopyResponse(
				domain.getId().value(),
				domain.getAcquisitionDate(),
				domain.getStatus(),
				domain.getBookId().value());
	}
}
