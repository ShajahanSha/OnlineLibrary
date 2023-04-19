package com.online.library.domain.cqrs.result;

import com.online.library.domain.constants.enums.TransactionStatus;
import com.online.library.domain.cqrs.command.BookCommand;
import com.online.library.domain.cqrs.command.CheckoutBookCommand;
import com.online.library.domain.cqrs.exception.ErrorCode;
import com.online.library.domain.cqrs.query.BookQuery;
import com.online.library.domain.model.StatusInfo;

/**
 * @author Shajahan.Shaik on 04/18/2023.
 */

public class EntityConverter {

    public static BookResult buildResult(BookCommand command) {
        return BookResult.builder()
                .bookId(command.getBookId())
                .name(command.getName())
                .author(command.getAuthor())
                .description(command.getDescription())
                .classification(command.getClassification())
                .isbn(command.getIsbn())
                .price(command.getPrice())
                .statusInfo(new StatusInfo(TransactionStatus.SUCCESS.toString(), null))
                .build();
    }

    public static BookResult populateBusinessExceptionResult(BookCommand command, ErrorCode errorCode) {
        BookResult subscriptionResult = new BookResult();
        subscriptionResult.setStatusInfo(new StatusInfo(TransactionStatus.FAILURE.toString(), errorCode));
        return subscriptionResult;
    }

    public static BookResult populateBusinessExceptionResult(BookQuery query, ErrorCode errorCode) {
        BookResult subscriptionResult = new BookResult();
        subscriptionResult.setStatusInfo(new StatusInfo(TransactionStatus.FAILURE.toString(), errorCode));
        return subscriptionResult;
    }


    public static CheckoutBookResult buildResult(CheckoutBookCommand command) {
        return CheckoutBookResult.builder()
                .checkoutCode(command.getCheckoutCode())
                .serviceType(command.getServiceType())
                .bookCommands(command.getBookCommands())
                .statusInfo(new StatusInfo(TransactionStatus.SUCCESS.toString(), null))
                .build();
    }

    public static CheckoutBookResult populateBusinessExceptionResult(CheckoutBookCommand command, ErrorCode errorCode) {
        CheckoutBookResult result = new CheckoutBookResult();
        result.setStatusInfo(new StatusInfo(TransactionStatus.FAILURE.toString(), errorCode));
        return result;
    }




}
