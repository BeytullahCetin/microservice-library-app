package com.turkcell.book_service.application.publisher.query;

import java.util.UUID;

import com.turkcell.book_service.application.publisher.dto.PublisherResponse;
import com.turkcell.book_service.core.cqrs.Query;

import jakarta.validation.constraints.NotNull;

public record FindByIdPublisherQuery(
		@NotNull UUID id)
		implements Query<PublisherResponse> {

}
