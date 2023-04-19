package com.online.library.domain.service;

import com.online.library.domain.cqrs.exception.BusinessException;
import com.online.library.domain.cqrs.query.BookQuery;
import com.online.library.domain.cqrs.result.BookResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by Shajahan.Shaik on 04/18/2023.
 */
public interface FetchBookService {

    Page<BookResult> fetchData(BookQuery query, Pageable pageable) throws BusinessException;
}
