package com.turkcell.book_service.application.bookcopy.mapper;

import org.springframework.stereotype.Component;

import com.turkcell.book_service.application.bookcopy.dto.ReturnedBookCopyResponse;
import com.turkcell.book_service.domain.bookcopy.model.BookCopy;

@Component
public class ReturnedBookCopyResponseMapper {
	public ReturnedBookCopyResponse toResponse(BookCopy domain) {
		return new ReturnedBookCopyResponse(
				domain.getId().value(),
				domain.getStatus());
	}
}
