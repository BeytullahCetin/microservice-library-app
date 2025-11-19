package com.turkcell.fine_service.service;

import com.turkcell.fine_service.dto.request.CreateFineRequest;
import com.turkcell.fine_service.dto.request.MarkFineAsPaidRequest;
import com.turkcell.fine_service.dto.response.FineResponse;
import java.util.List;
import java.util.UUID;

public interface FineService {

	FineResponse createFine(CreateFineRequest request);

	FineResponse getFine(UUID id);

	List<FineResponse> getFinesForBorrow(UUID borrowId);

	List<FineResponse> getOutstandingFines();

	FineResponse markFineAsPaid(UUID fineId, MarkFineAsPaidRequest request);
}

