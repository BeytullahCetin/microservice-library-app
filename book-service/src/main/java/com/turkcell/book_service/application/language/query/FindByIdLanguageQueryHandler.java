package com.turkcell.book_service.application.language.query;

import com.turkcell.book_service.application.language.dto.LanguageResponse;
import com.turkcell.book_service.application.language.mapper.LanguageResponseMapper;
import com.turkcell.book_service.core.cqrs.QueryHandler;
import com.turkcell.book_service.domain.language.model.LanguageId;
import com.turkcell.book_service.domain.language.repository.LanguageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindByIdLanguageQueryHandler implements QueryHandler<FindByIdLanguageQuery, LanguageResponse> {
    private final LanguageRepository languageRepository;
    private final LanguageResponseMapper languageResponseMapper;

    @Override
    public LanguageResponse handle(FindByIdLanguageQuery query) {
        return languageResponseMapper.toResponse(languageRepository.findById(new LanguageId(query.id())).get());
    }
}
