package com.turkcell.book_service.interfaces.web;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.book_service.application.author.command.CreateAuthorCommand;
import com.turkcell.book_service.application.author.dto.AuthorResponse;
import com.turkcell.book_service.application.author.dto.CreatedAuthorResponse;
import com.turkcell.book_service.application.author.query.FindByIdAuthorQuery;
import com.turkcell.book_service.application.author.query.ListAuthorsPagedQuery;
import com.turkcell.book_service.core.cqrs.CommandHandler;
import com.turkcell.book_service.core.cqrs.QueryHandler;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api")
@Validated
public class AuthorController {
	private final QueryHandler<ListAuthorsPagedQuery, List<AuthorResponse>> listAuthorQueryHandler;
	private final QueryHandler<FindByIdAuthorQuery, AuthorResponse> findByIdAuthorQueryHandler;
	private final CommandHandler<CreateAuthorCommand, CreatedAuthorResponse> createAuthorQueryHandler;

	public AuthorController(QueryHandler<ListAuthorsPagedQuery, List<AuthorResponse>> listAuthorQueryHandler,
			QueryHandler<FindByIdAuthorQuery, AuthorResponse> findByIdAuthorQueryHandler,
			CommandHandler<CreateAuthorCommand, CreatedAuthorResponse> createAuthorQueryHandler) {
		this.listAuthorQueryHandler = listAuthorQueryHandler;
		this.findByIdAuthorQueryHandler = findByIdAuthorQueryHandler;
		this.createAuthorQueryHandler = createAuthorQueryHandler;
	}

	@GetMapping("/v1/authors")
	public List<AuthorResponse> getAuthorsPaged(@Valid ListAuthorsPagedQuery query) {
		return listAuthorQueryHandler.handle(query);
	}

	@GetMapping("/v1/authors/{id}")
	public AuthorResponse getAuthorsPaged(@Valid FindByIdAuthorQuery query) {
		return findByIdAuthorQueryHandler.handle(query);
	}

	@PostMapping("/v1/authors")
	public CreatedAuthorResponse createAuthor(@RequestBody CreateAuthorCommand command) {
		return createAuthorQueryHandler.handle(command);
	}

}
