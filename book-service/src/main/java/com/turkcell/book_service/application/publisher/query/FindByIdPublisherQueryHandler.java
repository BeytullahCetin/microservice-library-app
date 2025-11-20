package com.turkcell.book_service.application.publisher.query;

import org.springframework.stereotype.Component;

import com.turkcell.book_service.application.publisher.dto.PublisherResponse;
import com.turkcell.book_service.application.publisher.mapper.PublisherResponseMapper;
import com.turkcell.book_service.core.cqrs.QueryHandler;
import com.turkcell.book_service.domain.publisher.model.PublisherId;
import com.turkcell.book_service.domain.publisher.repository.PublisherRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindByIdPublisherQueryHandler implements QueryHandler<FindByIdPublisherQuery, PublisherResponse> {
	private final PublisherRepository publisherRepository;
	private final PublisherResponseMapper publisherResponseMapper;

	@Override
	public PublisherResponse handle(FindByIdPublisherQuery query) {
		return publisherRepository
				.findById(new PublisherId(query.id()))
				.map(publisherResponseMapper::toResponse)
				.orElseThrow(() -> new RuntimeException("Publisher not found with id: " + query.id()));
	}

}
