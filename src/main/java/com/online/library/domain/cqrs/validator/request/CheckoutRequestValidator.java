/**
 *
 */
package com.online.library.domain.cqrs.validator.request;

import com.online.library.domain.constants.enums.PromotionCode;
import com.online.library.domain.cqrs.command.BookCommand;
import com.online.library.domain.cqrs.command.CheckoutBookCommand;
import com.online.library.domain.cqrs.exception.BusinessException;
import com.online.library.domain.cqrs.exception.ErrorCode;
import com.online.library.domain.cqrs.query.BookQuery;
import com.online.library.domain.cqrs.result.BookResult;
import com.online.library.domain.service.LibraryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Shajahan.Shaik
 */
@Component
public class CheckoutRequestValidator {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookRequestValidator.class);

    private final LibraryService bookService;
    public CheckoutRequestValidator(LibraryService bookService) {
        this.bookService = bookService;
    }

    public void validate(CheckoutBookCommand command) throws BusinessException {
        if (StringUtils.isEmpty(command.getCheckoutCode())) {
            throw new BusinessException(new ErrorCode("CHECKOUT_CODE_REQUIRED", "Checkout code required"));
        }
        validatePromotionCode(command.getPromotionCode());
        validateBookCommand(command.getBookCommands());

    }

    private void validatePromotionCode(String promotionCode) throws BusinessException{
        if (StringUtils.isEmpty(promotionCode)) {
            return;
        }
        Long id = PromotionCode.getCodeByValue(promotionCode);
        if (null == id || id.longValue() <= 0) {
            throw new BusinessException(new ErrorCode("INVALID_PROMOTION_CODE", "Promotion code is invalid"));
        }
    }

    private void validateBookCommand(List<BookCommand> bookCommands) throws BusinessException{
        if (CollectionUtils.isEmpty(bookCommands)) {
            throw new BusinessException(new ErrorCode("CHECKOUT_DATA_REQUIRED", "Please add atleast one book for checkout"));
        }
        for(BookCommand command : bookCommands) {
            if (command.getBookId() <= 0) {
                throw new BusinessException(new ErrorCode("INVALID_BOOKID", "BookId is invalid"));
            }
            BookQuery query = new BookQuery();
            query.setBookId(command.getBookId());
            BookResult result = bookService.fetchBookById(query.getBookId());
            if (null == result) {
                throw new BusinessException(new ErrorCode("INVALID_BOOKID", "BookId is invalid"));
            }
        }
    }


}
