package com.turkcell.book_service.infrastructure.persistence;

import com.turkcell.book_service.domain.entities.Translator;
import com.turkcell.book_service.domain.repositories.TranslatorRepository;
import com.turkcell.book_service.infrastructure.persistence.entity.JpaTranslatorEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Adapter implementation for TranslatorRepository
 */
@Component
public class TranslatorRepositoryAdapter implements TranslatorRepository {
    
    private final JpaTranslatorRepository jpaTranslatorRepository;
    
    public TranslatorRepositoryAdapter(JpaTranslatorRepository jpaTranslatorRepository) {
        this.jpaTranslatorRepository = jpaTranslatorRepository;
    }
    
    @Override
    public Translator save(Translator translator) {
        JpaTranslatorEntity entity = JpaTranslatorEntity.fromDomain(translator);
        JpaTranslatorEntity saved = jpaTranslatorRepository.save(entity);
        return saved.toDomain();
    }
    
    @Override
    public Optional<Translator> findById(UUID id) {
        return jpaTranslatorRepository.findById(id.toString())
                .map(JpaTranslatorEntity::toDomain);
    }
    
    @Override
    public List<Translator> findAll() {
        return jpaTranslatorRepository.findAll().stream()
                .map(JpaTranslatorEntity::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Translator> findByLastName(String lastName) {
        return jpaTranslatorRepository.findByLastName(lastName).stream()
                .map(JpaTranslatorEntity::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Translator> findByFirstNameAndLastName(String firstName, String lastName) {
        return jpaTranslatorRepository.findByFirstNameAndLastName(firstName, lastName).stream()
                .map(JpaTranslatorEntity::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Translator> findByNationality(String nationality) {
        return jpaTranslatorRepository.findByNationality(nationality).stream()
                .map(JpaTranslatorEntity::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public void deleteById(UUID id) {
        jpaTranslatorRepository.deleteById(id.toString());
    }
    
    @Override
    public boolean existsById(UUID id) {
        return jpaTranslatorRepository.existsById(id.toString());
    }
    
    @Override
    public long count() {
        return jpaTranslatorRepository.count();
    }
}

