package com.turkcell.book_service.application.translator.query;

import org.springframework.stereotype.Component;

import com.turkcell.book_service.application.translator.dto.TranslatorResponse;
import com.turkcell.book_service.application.translator.mapper.TranslatorResponseMapper;
import com.turkcell.book_service.core.cqrs.QueryHandler;
import com.turkcell.book_service.domain.translator.model.TranslatorId;
import com.turkcell.book_service.domain.translator.repository.TranslatorRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindByIdTranslatorQueryHandler implements QueryHandler<FindByIdTranslatorQuery, TranslatorResponse> {

	private final TranslatorRepository translatorRepository;
	private final TranslatorResponseMapper translatorResponseMapper;

	@Override
	public TranslatorResponse handle(FindByIdTranslatorQuery query) {
		return translatorResponseMapper.toResponse(translatorRepository.findById(new TranslatorId(query.id())).get());
	}

}
