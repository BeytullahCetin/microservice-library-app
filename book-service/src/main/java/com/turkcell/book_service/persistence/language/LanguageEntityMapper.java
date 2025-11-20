package com.turkcell.book_service.persistence.language;

import org.springframework.stereotype.Component;

import com.turkcell.book_service.domain.language.model.Language;
import com.turkcell.book_service.domain.language.model.LanguageId;

@Component
public class LanguageEntityMapper {
	public LanguageJpaEntity toEntity(Language domain) {
		LanguageJpaEntity entity = new LanguageJpaEntity();
		entity.setId(domain.getId().value());
		entity.setName(domain.getName());
		return entity;
	}

	public Language toDomain(LanguageJpaEntity entity) {
		return Language.rehydrate(new LanguageId(entity.getId()),
				entity.getName());
	}
}
