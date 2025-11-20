package com.turkcell.book_service.persistence.publisher;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.turkcell.book_service.domain.publisher.model.Publisher;
import com.turkcell.book_service.domain.publisher.model.PublisherId;
import com.turkcell.book_service.domain.publisher.repository.PublisherRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PublisherRepositoryAdapter implements PublisherRepository {

	private final PublisherJpaRepository repository;
	private final PublisherEntityMapper publisherEntityMapper;

	@Override
	public Optional<Publisher> findById(PublisherId id) {
		return repository
				.findById(Objects.requireNonNull(id.value()))
				.map(publisherEntityMapper::toDomain);
	}

	@Override
	public List<Publisher> findAllPaged(Integer pageIndex, Integer pageSize) {
		return repository
				.findAll(PageRequest.of(pageIndex, pageSize))
				.stream()
				.map(publisherEntityMapper::toDomain)
				.toList();
	}

	@Override
	public Publisher save(Publisher publisher) {
		PublisherJpaEntity entity = publisherEntityMapper.toEntity(publisher);
		entity = repository.save(Objects.requireNonNull(entity));
		return publisherEntityMapper.toDomain(entity);
	}

	@Override
	public void delete(PublisherId id) {
		repository.deleteById(Objects.requireNonNull(id.value()));
	}

}
