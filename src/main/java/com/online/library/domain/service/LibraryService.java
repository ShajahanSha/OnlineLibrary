package com.online.library.domain.service;

import com.online.library.domain.cqrs.command.BookCommand;
import com.online.library.domain.cqrs.exception.BusinessException;
import com.online.library.domain.cqrs.query.BookQuery;
import com.online.library.domain.cqrs.result.BookResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by Shajahan.Shaik on 04/18/2023.
 */
public interface LibraryService {
    BookResult processBook(BookCommand command) throws BusinessException;
    BookResult fetchBookById(long bookId) throws BusinessException;
    Page<BookResult> fetchBooks(BookQuery query, Pageable pageable) throws BusinessException;
}
