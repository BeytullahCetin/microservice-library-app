package com.turkcell.book_service.interfaces.web;

import com.turkcell.book_service.application.language.dto.LanguageResponse;
import com.turkcell.book_service.application.language.query.FindByIdLanguageQuery;
import com.turkcell.book_service.application.language.query.ListLanguagesPagedQuery;
import com.turkcell.book_service.core.cqrs.QueryHandler;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
public class LanguageController {
    private final QueryHandler<ListLanguagesPagedQuery, List<LanguageResponse>> listLanguageQueryHandler;
    private final QueryHandler<FindByIdLanguageQuery, LanguageResponse> findByIdLanguageQueryHandler;

    public LanguageController(QueryHandler<ListLanguagesPagedQuery, List<LanguageResponse>> listLanguageQueryHandler,
            QueryHandler<FindByIdLanguageQuery, LanguageResponse> findByIdLanguageQueryHandler) {
        this.listLanguageQueryHandler = listLanguageQueryHandler;
        this.findByIdLanguageQueryHandler = findByIdLanguageQueryHandler;
    }

    @GetMapping("/v1/languages")
    public List<LanguageResponse> getLanguagesPaged(@Valid ListLanguagesPagedQuery query) {
        return listLanguageQueryHandler.handle(query);
    }

    @GetMapping("/v1/languages/{id}")
    public LanguageResponse getLanguagesPaged(@Valid FindByIdLanguageQuery query) {
        return findByIdLanguageQueryHandler.handle(query);
    }

}
