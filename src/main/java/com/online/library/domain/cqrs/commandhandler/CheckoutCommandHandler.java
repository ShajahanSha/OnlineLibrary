package com.online.library.domain.cqrs.commandhandler;

import com.online.library.domain.cqrs.command.CheckoutBookCommand;
import com.online.library.domain.cqrs.command.CommandHandlerExecutor;
import com.online.library.domain.cqrs.exception.BusinessException;
import com.online.library.domain.cqrs.result.CheckoutBookResult;
import com.online.library.domain.cqrs.result.EntityConverter;
import com.online.library.domain.cqrs.validator.request.CheckoutRequestValidator;
import com.online.library.domain.service.CheckoutService;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Shajahan.Shaik on 04/18/2023.
 */

@Component
public class CheckoutCommandHandler extends CommandHandlerExecutor<CheckoutBookCommand, CheckoutBookResult> {
    private static final Logger logger = LoggerFactory.getLogger(BookCommandHandler.class);
    private final CheckoutRequestValidator requestValidator;
    private final CheckoutService checkoutBookService;

    public CheckoutCommandHandler(CheckoutRequestValidator requestValidator, CheckoutService checkoutBookService) {
        this.requestValidator = requestValidator;
        this.checkoutBookService = checkoutBookService;
    }

    @Override
    public CheckoutBookResult handle(CheckoutBookCommand command) throws Exception {
        logger.debug("CheckoutHandler | handle");
        CheckoutBookResult result = null;
        try {
            requestValidator.validate(command);
            result = checkoutBookService.processCheckout(command);
        } catch (BusinessException ex) {
            logger.error("CheckoutHandler | handle | BusinessException thrown. message -{}, detailedMessage -{} ", ex.getMessage(), ex);
            result = EntityConverter.populateBusinessExceptionResult(command, ex.getErrorCode());
        }
        return result;
    }

}
