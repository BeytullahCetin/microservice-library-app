package com.turkcell.book_service.application.book.query;

import com.turkcell.book_service.application.book.dto.BookResponse;
import com.turkcell.book_service.application.book.mapper.BookResponseMapper;
import com.turkcell.book_service.core.cqrs.QueryHandler;
import com.turkcell.book_service.domain.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ListBooksPagedQueryHandler implements QueryHandler<ListBooksPagedQuery, List<BookResponse>> {
    private final BookRepository bookRepository;
    private final BookResponseMapper bookResponseMapper;

    @Override
    public List<BookResponse> handle(ListBooksPagedQuery query) {
        return bookRepository
                .findAllPaged(query.pageIndex(), query.pageSize())
                .stream()
                .map(bookResponseMapper::toResponse)
                .toList();
    }

}
