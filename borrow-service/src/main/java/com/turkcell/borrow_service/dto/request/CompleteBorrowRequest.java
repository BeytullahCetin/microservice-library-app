package com.turkcell.borrow_service.dto.request;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record CompleteBorrowRequest(@NotNull LocalDate returnDate) {
}

