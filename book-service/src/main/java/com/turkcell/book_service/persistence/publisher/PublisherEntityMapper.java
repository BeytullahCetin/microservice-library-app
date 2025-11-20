package com.turkcell.book_service.persistence.publisher;

import org.springframework.stereotype.Component;

import com.turkcell.book_service.domain.publisher.model.Publisher;
import com.turkcell.book_service.domain.publisher.model.PublisherId;

@Component
public class PublisherEntityMapper {
	public PublisherJpaEntity toEntity(Publisher domain) {
		PublisherJpaEntity entity = new PublisherJpaEntity();
		entity.setId(domain.getId().value());
		entity.setName(domain.getName());
		entity.setAddress(domain.getAddress());

		return entity;
	}

	public Publisher toDomain(PublisherJpaEntity entity) {
		return Publisher.rehydrate(
				new PublisherId(entity.getId()),
				entity.getName(),
				entity.getAddress());
	}
}
