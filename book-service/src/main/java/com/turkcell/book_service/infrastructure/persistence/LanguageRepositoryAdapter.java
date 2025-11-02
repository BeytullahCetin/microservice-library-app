package com.turkcell.book_service.infrastructure.persistence;

import com.turkcell.book_service.domain.entities.Language;
import com.turkcell.book_service.domain.repositories.LanguageRepository;
import com.turkcell.book_service.infrastructure.persistence.entity.JpaLanguageEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Adapter implementation for LanguageRepository
 */
@Component
public class LanguageRepositoryAdapter implements LanguageRepository {
    
    private final JpaLanguageRepository jpaLanguageRepository;
    
    public LanguageRepositoryAdapter(JpaLanguageRepository jpaLanguageRepository) {
        this.jpaLanguageRepository = jpaLanguageRepository;
    }
    
    @Override
    public Language save(Language language) {
        JpaLanguageEntity entity = JpaLanguageEntity.fromDomain(language);
        JpaLanguageEntity saved = jpaLanguageRepository.save(entity);
        return saved.toDomain();
    }
    
    @Override
    public Optional<Language> findById(UUID id) {
        return jpaLanguageRepository.findById(id.toString())
                .map(JpaLanguageEntity::toDomain);
    }
    
    @Override
    public Optional<Language> findByCode(String code) {
        return jpaLanguageRepository.findByCode(code)
                .map(JpaLanguageEntity::toDomain);
    }
    
    @Override
    public List<Language> findAll() {
        return jpaLanguageRepository.findAll().stream()
                .map(JpaLanguageEntity::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public void deleteById(UUID id) {
        jpaLanguageRepository.deleteById(id.toString());
    }
    
    @Override
    public boolean existsByCode(String code) {
        return jpaLanguageRepository.existsByCode(code);
    }
    
    @Override
    public long count() {
        return jpaLanguageRepository.count();
    }
}

