package com.turkcell.book_service.application.translator.command;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.turkcell.book_service.application.translator.dto.DeletedTranslatorResponse;
import com.turkcell.book_service.application.translator.mapper.DeleteTranslatorMapper;
import com.turkcell.book_service.core.cqrs.CommandHandler;
import com.turkcell.book_service.domain.translator.model.Translator;
import com.turkcell.book_service.domain.translator.model.TranslatorId;
import com.turkcell.book_service.domain.translator.repository.TranslatorRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DeleteTranslatorCommandHandler
		implements CommandHandler<DeleteTranslatorCommand, DeletedTranslatorResponse> {

	private final TranslatorRepository translatorRepository;
	private final DeleteTranslatorMapper deleteTranslatorMapper;

	@Override
	public DeletedTranslatorResponse handle(DeleteTranslatorCommand command) {
		Optional<Translator> translator = translatorRepository.findById(new TranslatorId(command.id()));

		if (translator == null) {
			throw new IllegalArgumentException("Bu id ile bir translator bulunamadı!");
		}

		translatorRepository.delete(translator.get().getId());
		return deleteTranslatorMapper.toResponse(translator.get());
	}

}
