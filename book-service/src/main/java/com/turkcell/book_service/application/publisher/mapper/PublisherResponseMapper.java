package com.turkcell.book_service.application.publisher.mapper;

import org.springframework.stereotype.Component;

import com.turkcell.book_service.application.publisher.dto.PublisherResponse;
import com.turkcell.book_service.domain.publisher.model.Publisher;

@Component
public class PublisherResponseMapper {
	public PublisherResponse toResponse(Publisher domain) {
		return new PublisherResponse(
				domain.getId().value(),
				domain.getName(),
				domain.getAddress());
	}
}
