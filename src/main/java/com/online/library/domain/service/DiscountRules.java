package com.online.library.domain.service;

import com.online.library.domain.cqrs.command.BookCommand;

import java.math.BigDecimal;
import java.util.List;

public interface DiscountRules {
    BigDecimal apply(List<BookCommand> books);
}
