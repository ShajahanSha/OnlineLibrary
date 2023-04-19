/**
 *
 */
package com.online.library.domain.cqrs.validator.request;

import com.online.library.domain.constants.enums.ClassificationType;
import com.online.library.domain.constants.enums.ServiceType;
import com.online.library.domain.cqrs.command.BookCommand;
import com.online.library.domain.cqrs.exception.BusinessException;
import com.online.library.domain.cqrs.exception.ErrorCode;
import com.online.library.domain.cqrs.query.BookQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Shajahan.Shaik
 */
@Component
public class BookRequestValidator {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookRequestValidator.class);

    public void validate(BookCommand command) throws BusinessException {
        if (ServiceType.CREATE.getValue().equalsIgnoreCase(command.getServiceType())) {
            validateFullData(command);
        } else if (ServiceType.UPDATE.getValue().equalsIgnoreCase(command.getServiceType())) {
            if (command.getBookId() <= 0) {
                throw new BusinessException(new ErrorCode("BOOK_ID_REQUIRED", "BookId is required"));
            }
        } else if (ServiceType.DELETE.getValue().equalsIgnoreCase(command.getServiceType())) {
            if (command.getBookId() <= 0) {
                throw new BusinessException(new ErrorCode("BOOK_ID_REQUIRED", "BookId is required"));
            }
        }
    }

    private void validateFullData(BookCommand command) throws BusinessException {
        if (StringUtils.isEmpty(command.getName())) {
            throw new BusinessException(new ErrorCode("BOOK_NAME_REQUIRED", "Book Name required"));
        }
        if (StringUtils.isEmpty(command.getAuthor())) {
            throw new BusinessException(new ErrorCode("AUTHOR_REQUIRED", "Author required"));
        }
        if (StringUtils.isEmpty(command.getClassification())) {
            throw new BusinessException(new ErrorCode("CLASSIFICATION_REQUIRED", "Classification required"));
        }
        if (null == command.getPrice() || command.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException(new ErrorCode("INVALID_PRICE", "Invalid price type"));
        }
        if (StringUtils.isEmpty(command.getIsbn())) {
            throw new BusinessException(new ErrorCode("ISBN_REQUIRED", "ISBN required"));
        }
        validateClassification(command.getClassification());
        validateISBN(command.getIsbn());
    }

    private void validateISBN(String isbn) throws BusinessException {
        try {
            Long num = Long.valueOf(isbn.replace("-", ""));

            // TODO: 19/04/2023
            //isbnFormatCheck(isbn);
        } catch (Exception e) {
            throw new BusinessException(new ErrorCode("INVALID_ISBN", "Invalid Isbn, it should be number"));
        }
    }

    private void validateClassification(String classification) throws BusinessException{
        Long type = ClassificationType.getCodeByValue(classification);
        if (null == type || type.longValue() == 0l) {
            throw new BusinessException(new ErrorCode("INVALID_CLASSIFICATION", "Invalid classification"));
        }
    }


    public void validate(BookQuery query) throws BusinessException {

    }
}
