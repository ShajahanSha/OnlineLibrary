package com.online.library.domain.cqrs.result;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.online.library.domain.cqrs.command.BookCommand;
import com.online.library.domain.cqrs.exception.ErrorCode;
import com.online.library.domain.model.StatusInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;


/**
 * Created by Shajahan.Shaik on 04/18/2023.
 */

@Getter
@Setter
@Builder
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CheckoutBookResult extends BaseResult {
    private String checkoutCode;
    private String serviceType;
    private BigDecimal totalCheckoutAmount;
    private BigDecimal totalPayableAmount;
    private BigDecimal discountAmount;
    private BigDecimal promotionAmount;
    private List<BookCommand> bookCommands;
    private ErrorCode errorMessage;
    private StatusInfo statusInfo;

    public CheckoutBookResult() {

    }

    public CheckoutBookResult(String checkoutCode, String serviceType, BigDecimal totalCheckoutAmount,
                              BigDecimal totalPayableAmount, BigDecimal discountAmount, BigDecimal promotionAmount, List<BookCommand> bookCommands, ErrorCode errorMessage, StatusInfo statusInfo) {
        this.checkoutCode = checkoutCode;
        this.serviceType = serviceType;
        this.totalCheckoutAmount = totalCheckoutAmount;
        this.totalPayableAmount = totalPayableAmount;
        this.discountAmount = discountAmount;
        this.promotionAmount = promotionAmount;
        this.bookCommands = bookCommands;
        this.errorMessage = errorMessage;
        this.statusInfo = statusInfo;
    }

    @JsonIgnore
    public CheckoutBookResult filter() {

        return this;
    }

}
