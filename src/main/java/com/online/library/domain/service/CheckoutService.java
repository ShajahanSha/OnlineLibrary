package com.online.library.domain.service;

import com.online.library.domain.cqrs.command.CheckoutBookCommand;
import com.online.library.domain.cqrs.exception.BusinessException;
import com.online.library.domain.cqrs.result.CheckoutBookResult;

/**
 * Created by Shajahan.Shaik on 04/18/2023.
 */
public interface CheckoutService {
    CheckoutBookResult processCheckout(CheckoutBookCommand command) throws BusinessException;
}
