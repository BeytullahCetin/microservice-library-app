package com.turkcell.book_service.persistence.translator;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.turkcell.book_service.domain.translator.model.Translator;
import com.turkcell.book_service.domain.translator.model.TranslatorId;
import com.turkcell.book_service.domain.translator.repository.TranslatorRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TranslatorRepositoryAdapter implements TranslatorRepository {

	private final TranslatorJpaRepository repository;
	private final TranslatorEntityMapper translatorEntityMapper;

	@Override
	public Optional<Translator> findById(TranslatorId id) {
		return repository
				.findById(Objects.requireNonNull(id.value()))
				.map(translatorEntityMapper::toDomain);

	}

	@Override
	public List<Translator> findAllPaged(Integer pageIndex, Integer pageSize) {
		return repository
				.findAll(PageRequest.of(pageIndex, pageSize))
				.stream()
				.map(translatorEntityMapper::toDomain)
				.toList();
	}

	@Override
	public Translator save(Translator translator) {
		TranslatorJpaEntity entity = translatorEntityMapper.toEntity(translator);
		entity = repository.save(Objects.requireNonNull(entity));
		return translatorEntityMapper.toDomain(entity);
	}

	@Override
	public void delete(TranslatorId id) {
		repository.deleteById(Objects.requireNonNull(id.value()));
	}

}