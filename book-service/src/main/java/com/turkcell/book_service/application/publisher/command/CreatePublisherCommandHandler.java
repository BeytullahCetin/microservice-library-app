package com.turkcell.book_service.application.publisher.command;

import org.springframework.stereotype.Component;

import com.turkcell.book_service.application.publisher.dto.CreatedPublisherResponse;
import com.turkcell.book_service.application.publisher.mapper.CreatePublisherMapper;
import com.turkcell.book_service.core.cqrs.CommandHandler;
import com.turkcell.book_service.domain.publisher.model.Publisher;
import com.turkcell.book_service.domain.publisher.repository.PublisherRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreatePublisherCommandHandler implements CommandHandler<CreatePublisherCommand, CreatedPublisherResponse> {

	private final PublisherRepository publisherRepository;
	private final CreatePublisherMapper createPublisherMapper;

	@Override
	public CreatedPublisherResponse handle(CreatePublisherCommand command) {
		Publisher publisher = createPublisherMapper.toDomain(command);
		publisher = publisherRepository.save(publisher);
		return createPublisherMapper.toResponse(publisher);
	}
}
