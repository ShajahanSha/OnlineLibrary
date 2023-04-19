package com.online.library.domain.cqrs.commandhandler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.online.library.domain.cqrs.command.BookCommand;
import com.online.library.domain.cqrs.command.CommandHandlerExecutor;
import com.online.library.domain.cqrs.exception.BusinessException;
import com.online.library.domain.cqrs.result.BookResult;
import com.online.library.domain.cqrs.result.EntityConverter;
import com.online.library.domain.cqrs.validator.request.BookRequestValidator;
import com.online.library.domain.service.LibraryService;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Shajahan.Shaik on 04/18/2023.
 */

@Component
public class BookCommandHandler extends CommandHandlerExecutor<BookCommand, BookResult> {

    private static final Logger logger = LoggerFactory.getLogger(BookCommandHandler.class);
    private final BookRequestValidator requestValidator;
    private final LibraryService bookService;

    public BookCommandHandler(BookRequestValidator requestValidator, LibraryService bookService) {
        this.requestValidator = requestValidator;
        this.bookService = bookService;
    }

    @Override
    public BookResult handle(BookCommand command) throws Exception {
        BookResult result = null;
        try {
            requestValidator.validate(command);
            result = bookService.processBook(command);
        } catch (BusinessException ex) {
            logger.error("BusinessException thrown. message -{}, detailedMessage -{} ", ex.getMessage(), ex);
            result = EntityConverter.populateBusinessExceptionResult(command, ex.getErrorCode());
        }
        return result;
    }

}
