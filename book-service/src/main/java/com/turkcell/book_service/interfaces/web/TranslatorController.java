package com.turkcell.book_service.interfaces.web;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.book_service.application.translator.command.CreateTranslatorCommand;
import com.turkcell.book_service.application.translator.dto.CreatedTranslatorResponse;
import com.turkcell.book_service.application.translator.dto.TranslatorResponse;
import com.turkcell.book_service.application.translator.query.FindByIdTranslatorQuery;
import com.turkcell.book_service.application.translator.query.ListTranslatorsPagedQuery;
import com.turkcell.book_service.core.cqrs.CommandHandler;
import com.turkcell.book_service.core.cqrs.QueryHandler;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api")
@Validated
@RequiredArgsConstructor
public class TranslatorController {
	private final QueryHandler<ListTranslatorsPagedQuery, List<TranslatorResponse>> listTranslatorQueryHandler;
	private final QueryHandler<FindByIdTranslatorQuery, TranslatorResponse> findByIdTranslatorQueryHandler;
	private final CommandHandler<CreateTranslatorCommand, CreatedTranslatorResponse> createTranslatorQueryHandler;

	@GetMapping("/v1/translators")
	public List<TranslatorResponse> getTranslatorsPaged(@Valid ListTranslatorsPagedQuery query) {
		return listTranslatorQueryHandler.handle(query);
	}

	@GetMapping("/v1/translators/{id}")
	public TranslatorResponse getTranslatorsPaged(@Valid FindByIdTranslatorQuery query) {
		return findByIdTranslatorQueryHandler.handle(query);
	}

	@PostMapping("/v1/translators")
	public CreatedTranslatorResponse createTranslator(@RequestBody CreateTranslatorCommand command) {
		return createTranslatorQueryHandler.handle(command);
	}

}
