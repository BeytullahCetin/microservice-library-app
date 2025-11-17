package com.turkcell.borrow_service.service;

import com.turkcell.borrow_service.dto.request.CompleteBorrowRequest;
import com.turkcell.borrow_service.dto.request.CreateBorrowRequest;
import com.turkcell.borrow_service.dto.response.BorrowResponse;
import java.util.List;
import java.util.UUID;

public interface BorrowService {

	BorrowResponse createBorrow(CreateBorrowRequest request);

	BorrowResponse getBorrow(UUID id);

	List<BorrowResponse> getBorrows(boolean activeOnly);

	BorrowResponse completeBorrow(UUID borrowId, CompleteBorrowRequest request);
}

