package com.online.library.domain.cqrs.command;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.online.library.domain.cqrs.result.CheckoutBookResult;
import com.online.library.domain.cqrs.validator.ValidateBookCommand;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Created by Shajahan.Shaik on 04/18/2023.
 */
@ValidateBookCommand
@ApiModel(value = "BookCommand")
@Getter
@Setter
@Builder
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CheckoutBookCommand implements Command<CheckoutBookResult> {

    private String checkoutCode;
    private String serviceType;
    private String promotionCode;
    private List<BookCommand> bookCommands;

    public CheckoutBookCommand() {

    }

    public CheckoutBookCommand(String checkoutCode, String serviceType, String promotionCode, List<BookCommand> bookCommands) {
        this.checkoutCode = checkoutCode;
        this.serviceType = serviceType;
        this.promotionCode = promotionCode;
        this.bookCommands = bookCommands;
    }
}
