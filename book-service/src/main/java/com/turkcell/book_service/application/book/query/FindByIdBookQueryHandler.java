package com.turkcell.book_service.application.book.query;

import com.turkcell.book_service.application.book.dto.BookResponse;
import com.turkcell.book_service.application.book.mapper.BookResponseMapper;
import com.turkcell.book_service.core.cqrs.QueryHandler;
import com.turkcell.book_service.domain.book.model.BookId;
import com.turkcell.book_service.domain.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindByIdBookQueryHandler implements QueryHandler<FindByIdBookQuery, BookResponse> {
    private final BookRepository bookRepository;
    private final BookResponseMapper bookResponseMapper;

    @Override
    public BookResponse handle(FindByIdBookQuery query) {
        return bookResponseMapper.toResponse(bookRepository.findById(new BookId(query.id())).get());
    }

}