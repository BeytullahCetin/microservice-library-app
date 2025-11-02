package com.turkcell.book_service.infrastructure.persistence;

import com.turkcell.book_service.domain.entities.Book;
import com.turkcell.book_service.domain.repositories.BookRepository;
import com.turkcell.book_service.infrastructure.persistence.entity.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Adapter implementation for BookRepository
 * Converts between domain entities and JPA entities
 */
@Component
public class BookRepositoryAdapter implements BookRepository {
    
    private final JpaBookRepository jpaBookRepository;
    private final JpaPublisherRepository jpaPublisherRepository;
    private final JpaLanguageRepository jpaLanguageRepository;
    private final JpaAuthorRepository jpaAuthorRepository;
    private final JpaTranslatorRepository jpaTranslatorRepository;
    
    public BookRepositoryAdapter(JpaBookRepository jpaBookRepository,
                                 JpaPublisherRepository jpaPublisherRepository,
                                 JpaLanguageRepository jpaLanguageRepository,
                                 JpaAuthorRepository jpaAuthorRepository,
                                 JpaTranslatorRepository jpaTranslatorRepository) {
        this.jpaBookRepository = jpaBookRepository;
        this.jpaPublisherRepository = jpaPublisherRepository;
        this.jpaLanguageRepository = jpaLanguageRepository;
        this.jpaAuthorRepository = jpaAuthorRepository;
        this.jpaTranslatorRepository = jpaTranslatorRepository;
    }
    
    @Override
    public Book save(Book book) {
        JpaBookEntity entity = JpaBookEntity.fromDomain(book);
        
        // Fetch and set managed entities to avoid detached entity issues
        if (book.getPublisher() != null) {
            JpaPublisherEntity publisher = jpaPublisherRepository.findById(book.getPublisher().getId().toString())
                    .orElseGet(() -> {
                        JpaPublisherEntity newPublisher = JpaPublisherEntity.fromDomain(book.getPublisher());
                        return jpaPublisherRepository.save(newPublisher);
                    });
            entity.setPublisher(publisher);
        }
        
        if (book.getLanguage() != null) {
            JpaLanguageEntity language = jpaLanguageRepository.findById(book.getLanguage().getId().toString())
                    .orElseGet(() -> {
                        JpaLanguageEntity newLanguage = JpaLanguageEntity.fromDomain(book.getLanguage());
                        return jpaLanguageRepository.save(newLanguage);
                    });
            entity.setLanguage(language);
        }
        
        // Handle authors
        if (!book.getAuthors().isEmpty()) {
            List<JpaAuthorEntity> authors = book.getAuthors().stream()
                    .map(author -> jpaAuthorRepository.findById(author.getId().toString())
                            .orElseGet(() -> {
                                JpaAuthorEntity newAuthor = JpaAuthorEntity.fromDomain(author);
                                return jpaAuthorRepository.save(newAuthor);
                            }))
                    .collect(Collectors.toList());
            entity.setAuthors(authors);
        }
        
        // Handle translators
        if (!book.getTranslators().isEmpty()) {
            List<JpaTranslatorEntity> translators = book.getTranslators().stream()
                    .map(translator -> jpaTranslatorRepository.findById(translator.getId().toString())
                            .orElseGet(() -> {
                                JpaTranslatorEntity newTranslator = JpaTranslatorEntity.fromDomain(translator);
                                return jpaTranslatorRepository.save(newTranslator);
                            }))
                    .collect(Collectors.toList());
            entity.setTranslators(translators);
        }
        
        JpaBookEntity saved = jpaBookRepository.save(entity);
        return saved.toDomain();
    }
    
    @Override
    public Optional<Book> findById(UUID id) {
        return jpaBookRepository.findById(id.toString())
                .map(JpaBookEntity::toDomain);
    }
    
    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return jpaBookRepository.findByIsbn(isbn)
                .map(JpaBookEntity::toDomain);
    }
    
    @Override
    public List<Book> findAll() {
        return jpaBookRepository.findAll().stream()
                .map(JpaBookEntity::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Book> findByTitleContaining(String title) {
        return jpaBookRepository.findByTitleContainingIgnoreCase(title).stream()
                .map(JpaBookEntity::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Book> findByAuthorId(UUID authorId) {
        return jpaBookRepository.findByAuthorId(authorId.toString()).stream()
                .map(JpaBookEntity::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Book> findByPublisherId(UUID publisherId) {
        return jpaBookRepository.findByPublisherId(publisherId.toString()).stream()
                .map(JpaBookEntity::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Book> findByLanguageId(UUID languageId) {
        return jpaBookRepository.findByLanguageId(languageId.toString()).stream()
                .map(JpaBookEntity::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Book> findAvailableBooks() {
        return jpaBookRepository.findAvailableBooks().stream()
                .map(JpaBookEntity::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public void deleteById(UUID id) {
        jpaBookRepository.deleteById(id.toString());
    }
    
    @Override
    public boolean existsByIsbn(String isbn) {
        return jpaBookRepository.existsByIsbn(isbn);
    }
    
    @Override
    public long count() {
        return jpaBookRepository.count();
    }
}

