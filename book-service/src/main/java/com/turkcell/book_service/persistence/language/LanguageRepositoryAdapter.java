package com.turkcell.book_service.persistence.language;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.turkcell.book_service.domain.language.model.Language;
import com.turkcell.book_service.domain.language.model.LanguageId;
import com.turkcell.book_service.domain.language.repository.LanguageRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class LanguageRepositoryAdapter implements LanguageRepository {
	private final LanguageJpaRepository repository;
	private final LanguageEntityMapper languageEntityMapper;

	@Override
	public Optional<Language> findById(LanguageId id) {
		return repository
				.findById(Objects.requireNonNull(id.value()))
				.map(languageEntityMapper::toDomain);
	}

	@Override
	public List<Language> findAllPaged(Integer pageIndex, Integer pageSize) {
		return repository
				.findAll(PageRequest.of(pageIndex, pageSize))
				.stream()
				.map(languageEntityMapper::toDomain)
				.toList();
	}

	@Override
	public Language save(Language language) {
		LanguageJpaEntity entity = languageEntityMapper.toEntity(language);
		entity = repository.save(Objects.requireNonNull(entity));
		return languageEntityMapper.toDomain(entity);
	}

	@Override
	public void delete(LanguageId id) {
		repository.deleteById(Objects.requireNonNull(id.value()));
	}
}