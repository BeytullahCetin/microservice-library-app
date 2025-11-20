package com.turkcell.book_service.persistence.translator;

import org.springframework.stereotype.Component;

import com.turkcell.book_service.domain.translator.model.Translator;
import com.turkcell.book_service.domain.translator.model.TranslatorId;

@Component
public class TranslatorEntityMapper {

	public TranslatorJpaEntity toEntity(Translator domain) {
		TranslatorJpaEntity entity = new TranslatorJpaEntity();
		entity.setId(domain.getId().value());
		entity.setName(domain.getName());

		return entity;
	}

	public Translator toDomain(TranslatorJpaEntity entity) {
		return Translator.rehydrate(new TranslatorId(entity.getId()), entity.getName());
	}
}
