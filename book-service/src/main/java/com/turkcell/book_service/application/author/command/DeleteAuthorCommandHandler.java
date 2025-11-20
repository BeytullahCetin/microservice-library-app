package com.turkcell.book_service.application.author.command;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.turkcell.book_service.application.author.dto.DeletedAuthorResponse;
import com.turkcell.book_service.application.author.mapper.DeleteAuthorMapper;
import com.turkcell.book_service.core.cqrs.CommandHandler;
import com.turkcell.book_service.domain.author.model.Author;
import com.turkcell.book_service.domain.author.model.AuthorId;
import com.turkcell.book_service.domain.author.repository.AuthorRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DeleteAuthorCommandHandler implements CommandHandler<DeleteAuthorCommand, DeletedAuthorResponse> {

	private final AuthorRepository authorRepository;
	private final DeleteAuthorMapper deleteAuthorMapper;

	@Override
	public DeletedAuthorResponse handle(DeleteAuthorCommand command) {
		Optional<Author> author = authorRepository.findById(new AuthorId(command.id()));

		if (author == null) {
			throw new IllegalArgumentException("Bu id ile bir author bulunamadı!");
		}

		authorRepository.delete(author.get().getId());
		return deleteAuthorMapper.toResponse(author.get());
	}
}
