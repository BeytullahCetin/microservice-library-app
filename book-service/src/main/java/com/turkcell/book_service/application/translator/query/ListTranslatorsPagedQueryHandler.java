package com.turkcell.book_service.application.translator.query;

import java.util.List;

import org.springframework.stereotype.Component;

import com.turkcell.book_service.application.translator.dto.TranslatorResponse;
import com.turkcell.book_service.application.translator.mapper.TranslatorResponseMapper;
import com.turkcell.book_service.core.cqrs.QueryHandler;
import com.turkcell.book_service.domain.translator.repository.TranslatorRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ListTranslatorsPagedQueryHandler
		implements QueryHandler<ListTranslatorsPagedQuery, List<TranslatorResponse>> {

	private final TranslatorRepository translatorRepository;
	private final TranslatorResponseMapper translatorResponseMapper;

	@Override
	public List<TranslatorResponse> handle(ListTranslatorsPagedQuery query) {
		return translatorRepository
				.findAllPaged(query.pageIndex(), query.pageSize())
				.stream()
				.map(translatorResponseMapper::toResponse)
				.toList();
	}

}
