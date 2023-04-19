package com.online.library.domain.cqrs.commandhandler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.online.library.domain.cqrs.exception.BusinessException;
import com.online.library.domain.cqrs.query.BookQuery;
import com.online.library.domain.cqrs.query.QueryHandlerExecutor;
import com.online.library.domain.cqrs.result.BookResult;
import com.online.library.domain.cqrs.result.EntityConverter;
import com.online.library.domain.cqrs.validator.request.BookRequestValidator;
import com.online.library.domain.service.LibraryService;
import com.online.library.domain.service.FetchBookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

/**
 * Created by Shajahan.Shaik on 04/18/2023.
 */

@Component
public class FetchQueryHandler extends QueryHandlerExecutor<BookQuery, BookResult> {

    private static final Logger logger = LoggerFactory.getLogger(FetchQueryHandler.class);
    private final Gson gsonObj = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
    private final FetchBookService service;
    private final LibraryService bookService;
    private final BookRequestValidator requestValidator;

    public FetchQueryHandler(FetchBookService service, LibraryService bookService, BookRequestValidator requestValidator) {
        this.service = service;
        this.bookService = bookService;
        this.requestValidator = requestValidator;
    }

    public void process(BookQuery query) throws Exception{
        requestValidator.validate(query);
        handle(query);
    }

    @Override
    public BookResult handle(BookQuery query) throws Exception {
        BookResult result = null;
        try {
            result = bookService.fetchBooks(query);
        } catch (BusinessException ex) {
            logger.error("BusinessException thrown. message -{}, detailedMessage -{} ", ex.getMessage(), ex);
            result = EntityConverter.populateBusinessExceptionResult(query, ex.getErrorCode());
        }
        return result;
    }

    public Page<BookResult> fetchData(BookQuery searchCriteria, Pageable pageable) throws BusinessException {
        logger.info("FetchQuery | fetchPlan | START");
        return bookService.fetchBooks(searchCriteria, pageable);
    }

}
