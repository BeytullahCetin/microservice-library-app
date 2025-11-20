package com.turkcell.book_service.application.language.mapper;

import org.springframework.stereotype.Component;

import com.turkcell.book_service.application.language.dto.LanguageResponse;
import com.turkcell.book_service.domain.language.model.Language;

@Component
public class LanguageResponseMapper {
    public LanguageResponse toResponse(Language domain) {
        return new LanguageResponse(
                domain.getId().value(),
                domain.getName());
    }
}
