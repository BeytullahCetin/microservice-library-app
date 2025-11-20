package com.turkcell.book_service.domain.translator.repository;

import java.util.List;
import java.util.Optional;

import com.turkcell.book_service.domain.translator.model.Translator;
import com.turkcell.book_service.domain.translator.model.TranslatorId;

public interface TranslatorRepository {
	Optional<Translator> findById(TranslatorId id);

	List<Translator> findAllPaged(Integer pageIndex, Integer pageSize);

	Translator save(Translator translator);

	void delete(TranslatorId id);
}
