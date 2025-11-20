package com.turkcell.book_service.application.publisher.query;

import java.util.List;

import com.turkcell.book_service.application.publisher.dto.PublisherResponse;
import com.turkcell.book_service.core.cqrs.Query;

import jakarta.validation.constraints.Min;

public record ListPublishersPagedQuery(
		@Min(0) Integer pageIndex,
		@Min(1) Integer pageSize)
		implements Query<List<PublisherResponse>> {

}
