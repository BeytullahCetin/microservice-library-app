package com.turkcell.book_service.application.translator.mapper;

import org.springframework.stereotype.Component;

import com.turkcell.book_service.application.translator.dto.DeletedTranslatorResponse;
import com.turkcell.book_service.domain.translator.model.Translator;

@Component
public class DeleteTranslatorMapper {
	public DeletedTranslatorResponse toResponse(Translator domain) {
		return new DeletedTranslatorResponse(domain.getId().value(), domain.getName());
	}
}
