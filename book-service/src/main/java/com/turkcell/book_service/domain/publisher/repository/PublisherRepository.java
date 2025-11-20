package com.turkcell.book_service.domain.publisher.repository;

import java.util.List;
import java.util.Optional;

import com.turkcell.book_service.domain.publisher.model.Publisher;
import com.turkcell.book_service.domain.publisher.model.PublisherId;

public interface PublisherRepository {
	Optional<Publisher> findById(PublisherId id);

	List<Publisher> findAllPaged(Integer pageIndex, Integer pageSize);

	Publisher save(Publisher publisher);

	void delete(PublisherId id);
}
