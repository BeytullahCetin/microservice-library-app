package com.turkcell.fine_service.dto.request;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record MarkFineAsPaidRequest(@NotNull LocalDate paymentDate) {
}

