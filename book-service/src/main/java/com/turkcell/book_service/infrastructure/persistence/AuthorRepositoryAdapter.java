package com.turkcell.book_service.infrastructure.persistence;

import com.turkcell.book_service.domain.entities.Author;
import com.turkcell.book_service.domain.repositories.AuthorRepository;
import com.turkcell.book_service.infrastructure.persistence.entity.JpaAuthorEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Adapter implementation for AuthorRepository
 */
@Component
public class AuthorRepositoryAdapter implements AuthorRepository {
    
    private final JpaAuthorRepository jpaAuthorRepository;
    
    public AuthorRepositoryAdapter(JpaAuthorRepository jpaAuthorRepository) {
        this.jpaAuthorRepository = jpaAuthorRepository;
    }
    
    @Override
    public Author save(Author author) {
        JpaAuthorEntity entity = JpaAuthorEntity.fromDomain(author);
        JpaAuthorEntity saved = jpaAuthorRepository.save(entity);
        return saved.toDomain();
    }
    
    @Override
    public Optional<Author> findById(UUID id) {
        return jpaAuthorRepository.findById(id.toString())
                .map(JpaAuthorEntity::toDomain);
    }
    
    @Override
    public List<Author> findAll() {
        return jpaAuthorRepository.findAll().stream()
                .map(JpaAuthorEntity::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Author> findByLastName(String lastName) {
        return jpaAuthorRepository.findByLastName(lastName).stream()
                .map(JpaAuthorEntity::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Author> findByFirstNameAndLastName(String firstName, String lastName) {
        return jpaAuthorRepository.findByFirstNameAndLastName(firstName, lastName).stream()
                .map(JpaAuthorEntity::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Author> findByNationality(String nationality) {
        return jpaAuthorRepository.findByNationality(nationality).stream()
                .map(JpaAuthorEntity::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public void deleteById(UUID id) {
        jpaAuthorRepository.deleteById(id.toString());
    }
    
    @Override
    public boolean existsById(UUID id) {
        return jpaAuthorRepository.existsById(id.toString());
    }
    
    @Override
    public long count() {
        return jpaAuthorRepository.count();
    }
}

