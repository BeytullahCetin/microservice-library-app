package com.turkcell.book_service.application.author.query;

import org.springframework.stereotype.Component;

import com.turkcell.book_service.application.author.dto.AuthorResponse;
import com.turkcell.book_service.application.author.mapper.AuthorResponseMapper;
import com.turkcell.book_service.core.cqrs.QueryHandler;
import com.turkcell.book_service.domain.author.model.AuthorId;
import com.turkcell.book_service.domain.author.repository.AuthorRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindByIdAuthorQueryHandler implements QueryHandler<FindByIdAuthorQuery, AuthorResponse> {
	private final AuthorRepository authorRepository;
	private final AuthorResponseMapper authorResponseMapper;

	@Override
	public AuthorResponse handle(FindByIdAuthorQuery query) {
		return authorResponseMapper.toResponse(authorRepository.findById(new AuthorId(query.id())).get());
	}

}
