package com.turkcell.book_service.infrastructure.persistence;

import com.turkcell.book_service.domain.entities.Publisher;
import com.turkcell.book_service.domain.repositories.PublisherRepository;
import com.turkcell.book_service.infrastructure.persistence.entity.JpaPublisherEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Adapter implementation for PublisherRepository
 */
@Component
public class PublisherRepositoryAdapter implements PublisherRepository {
    
    private final JpaPublisherRepository jpaPublisherRepository;
    
    public PublisherRepositoryAdapter(JpaPublisherRepository jpaPublisherRepository) {
        this.jpaPublisherRepository = jpaPublisherRepository;
    }
    
    @Override
    public Publisher save(Publisher publisher) {
        JpaPublisherEntity entity = JpaPublisherEntity.fromDomain(publisher);
        JpaPublisherEntity saved = jpaPublisherRepository.save(entity);
        return saved.toDomain();
    }
    
    @Override
    public Optional<Publisher> findById(UUID id) {
        return jpaPublisherRepository.findById(id.toString())
                .map(JpaPublisherEntity::toDomain);
    }
    
    @Override
    public Optional<Publisher> findByName(String name) {
        return jpaPublisherRepository.findByName(name)
                .map(JpaPublisherEntity::toDomain);
    }
    
    @Override
    public List<Publisher> findAll() {
        return jpaPublisherRepository.findAll().stream()
                .map(JpaPublisherEntity::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Publisher> findByCountry(String country) {
        return jpaPublisherRepository.findByCountry(country).stream()
                .map(JpaPublisherEntity::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public void deleteById(UUID id) {
        jpaPublisherRepository.deleteById(id.toString());
    }
    
    @Override
    public boolean existsByName(String name) {
        return jpaPublisherRepository.existsByName(name);
    }
    
    @Override
    public long count() {
        return jpaPublisherRepository.count();
    }
}

