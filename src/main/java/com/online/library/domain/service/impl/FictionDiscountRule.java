package com.online.library.domain.service.impl;

import com.online.library.domain.constants.enums.ClassificationType;
import com.online.library.domain.cqrs.command.BookCommand;
import com.online.library.domain.service.DiscountRules;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;

@Component
public class FictionDiscountRule implements DiscountRules {

    public static final BigDecimal fictionDiscount = new BigDecimal("0.1");

    public FictionDiscountRule() {

    }

    @Override
    public BigDecimal apply(List<BookCommand> books) {
        BigDecimal discountAmount = BigDecimal.ZERO;
        if (!CollectionUtils.isEmpty(books)) {
            for(BookCommand command : books) {
                if (ClassificationType.FICTION.getValue().equalsIgnoreCase(command.getClassification())) {
                    if (fictionDiscount.compareTo(BigDecimal.ZERO) > 0) {
                        BigDecimal discount = command.getPrice().multiply(fictionDiscount);
                        discountAmount = discountAmount.add(discount);
                    }
                }
            }
        }
        return discountAmount;
    }
}
