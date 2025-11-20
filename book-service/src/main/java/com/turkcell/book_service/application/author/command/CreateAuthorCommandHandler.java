package com.turkcell.book_service.application.author.command;

import org.springframework.stereotype.Component;

import com.turkcell.book_service.application.author.dto.CreatedAuthorResponse;
import com.turkcell.book_service.application.author.mapper.CreateAuthorMapper;
import com.turkcell.book_service.core.cqrs.CommandHandler;
import com.turkcell.book_service.domain.author.model.Author;
import com.turkcell.book_service.domain.author.repository.AuthorRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreateAuthorCommandHandler implements CommandHandler<CreateAuthorCommand, CreatedAuthorResponse> {

	private final AuthorRepository authorRepository;
	private final CreateAuthorMapper createAuthorMapper;

	@Override
	public CreatedAuthorResponse handle(CreateAuthorCommand command) {
		Author author = createAuthorMapper.toDomain(command);
		author = authorRepository.save(author);
		return createAuthorMapper.toResponse(author);
	}
}
