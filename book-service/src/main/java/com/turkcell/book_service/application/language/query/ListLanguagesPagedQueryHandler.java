package com.turkcell.book_service.application.language.query;

import com.turkcell.book_service.application.language.dto.LanguageResponse;
import com.turkcell.book_service.application.language.mapper.LanguageResponseMapper;
import com.turkcell.book_service.core.cqrs.QueryHandler;
import com.turkcell.book_service.domain.language.repository.LanguageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ListLanguagesPagedQueryHandler implements QueryHandler<ListLanguagesPagedQuery, List<LanguageResponse>> {
    private final LanguageRepository languageRepository;
    private final LanguageResponseMapper languageResponseMapper;

    @Override
    public List<LanguageResponse> handle(ListLanguagesPagedQuery query) {
        return languageRepository
                .findAllPaged(query.pageIndex(), query.pageSize())
                .stream()
                .map(languageResponseMapper::toResponse)
                .toList();
    }

}
