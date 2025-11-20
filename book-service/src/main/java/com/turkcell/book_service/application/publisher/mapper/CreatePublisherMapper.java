package com.turkcell.book_service.application.publisher.mapper;

import org.springframework.stereotype.Component;

import com.turkcell.book_service.application.publisher.command.CreatePublisherCommand;
import com.turkcell.book_service.application.publisher.dto.CreatedPublisherResponse;
import com.turkcell.book_service.domain.publisher.model.Publisher;

@Component
public class CreatePublisherMapper {
	public Publisher toDomain(CreatePublisherCommand command) {
		return Publisher.create(command.name(), command.address());
	}

	public CreatedPublisherResponse toResponse(Publisher domain) {
		return new CreatedPublisherResponse(
				domain.getId().value(),
				domain.getName(),
				domain.getAddress());
	}
}
