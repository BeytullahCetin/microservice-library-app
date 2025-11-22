package com.turkcell.borrow_service.mapper;

import com.turkcell.borrow_service.dto.request.CreateBorrowRequest;
import com.turkcell.borrow_service.dto.response.BorrowResponse;
import com.turkcell.borrow_service.entity.Borrow;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class BorrowMapper {

	public Borrow toBorrow(CreateBorrowRequest request, UUID bookCopyId) {
		return Borrow.builder()
				.customerId(request.customerId())
				.bookCopyId(bookCopyId)
				.borrowDate(request.borrowDate())
				.dueDate(request.dueDate())
				.build();
	}

	public BorrowResponse toResponse(Borrow borrow) {
		return new BorrowResponse(
				borrow.getId(),
				borrow.getCustomerId(),
				borrow.getBookCopyId(),
				borrow.getBorrowDate(),
				borrow.getDueDate(),
				borrow.getReturnDate());
	}
}

