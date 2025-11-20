package com.turkcell.book_service.domain.language.repository;

import java.util.List;
import java.util.Optional;

import com.turkcell.book_service.domain.language.model.Language;
import com.turkcell.book_service.domain.language.model.LanguageId;

public interface LanguageRepository {
	Optional<Language> findById(LanguageId id);

	List<Language> findAllPaged(Integer pageIndex, Integer pageSize);

	Language save(Language language);

	void delete(LanguageId id);
}
