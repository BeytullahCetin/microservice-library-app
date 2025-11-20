package com.turkcell.book_service.application.translator.mapper;

import org.springframework.stereotype.Component;

import com.turkcell.book_service.application.translator.dto.TranslatorResponse;
import com.turkcell.book_service.domain.translator.model.Translator;

@Component
public class TranslatorResponseMapper {
	public TranslatorResponse toResponse(Translator domain) {
		return new TranslatorResponse(domain.getId().value(), domain.getName());
	}
}
