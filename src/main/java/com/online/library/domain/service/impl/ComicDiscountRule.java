package com.online.library.domain.service.impl;

import com.online.library.domain.constants.enums.ClassificationType;
import com.online.library.domain.cqrs.command.BookCommand;
import com.online.library.domain.service.DiscountRules;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;

@Component
public class ComicDiscountRule implements DiscountRules {

    public static final BigDecimal comicDiscount = new BigDecimal("0");

    public ComicDiscountRule() {
    }

    @Override
    public BigDecimal apply(List<BookCommand> books) {
        BigDecimal discountAmount = BigDecimal.ZERO;
        if (!CollectionUtils.isEmpty(books)) {
            for(BookCommand command : books) {
                if (ClassificationType.COMIC.getValue().equalsIgnoreCase(command.getClassification())) {
                    if (comicDiscount.compareTo(BigDecimal.ZERO) > 0) {
                        BigDecimal discount = command.getPrice().multiply(comicDiscount);
                        discountAmount = discountAmount.add(discount);
                    }
                }
            }
        }
        return discountAmount;
    }
}
