package com.turkcell.book_service.application.translator.command;

import org.springframework.stereotype.Component;

import com.turkcell.book_service.application.translator.dto.CreatedTranslatorResponse;
import com.turkcell.book_service.application.translator.mapper.CreateTranslatorMapper;
import com.turkcell.book_service.core.cqrs.CommandHandler;
import com.turkcell.book_service.domain.translator.model.Translator;
import com.turkcell.book_service.domain.translator.repository.TranslatorRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreateTranslatorCommandHandler
		implements CommandHandler<CreateTranslatorCommand, CreatedTranslatorResponse> {

	private final TranslatorRepository translatorRepository;
	private final CreateTranslatorMapper createTranslatorMapper;

	@Override
	public CreatedTranslatorResponse handle(CreateTranslatorCommand command) {
		Translator translator = createTranslatorMapper.toDomain(command);
		translator = translatorRepository.save(translator);
		return createTranslatorMapper.toResponse(translator);
	}

}
