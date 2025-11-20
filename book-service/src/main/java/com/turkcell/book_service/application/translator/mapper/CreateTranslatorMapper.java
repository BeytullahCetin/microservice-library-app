package com.turkcell.book_service.application.translator.mapper;

import org.springframework.stereotype.Component;

import com.turkcell.book_service.application.translator.command.CreateTranslatorCommand;
import com.turkcell.book_service.application.translator.dto.CreatedTranslatorResponse;
import com.turkcell.book_service.domain.translator.model.Translator;

@Component
public class CreateTranslatorMapper {
	public Translator toDomain(CreateTranslatorCommand command) {
		return Translator.create(command.name());
	}

	public CreatedTranslatorResponse toResponse(Translator domain) {
		return new CreatedTranslatorResponse(
				domain.getId().value(),
				domain.getName());
	}
}
