package com.online.library.domain.service.impl;

import com.online.library.domain.constants.enums.PromotionCode;
import com.online.library.domain.cqrs.command.BookCommand;
import com.online.library.domain.cqrs.command.CheckoutBookCommand;
import com.online.library.domain.cqrs.exception.BusinessException;
import com.online.library.domain.cqrs.result.CheckoutBookResult;
import com.online.library.domain.cqrs.result.EntityConverter;
import com.online.library.domain.service.CheckoutService;
import com.online.library.domain.service.DiscountRules;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

/**
 * Created by Shajahan.Shaik on 04/18/2023.
 */

@Service("checkoutBookService")
public class CheckoutServiceImpl implements CheckoutService {

    protected static final Logger logger = LoggerFactory.getLogger(LibraryServiceImpl.class);

    @Resource
    List<DiscountRules> discountRulesList;

    public CheckoutServiceImpl() {

    }

    @Override
    @Transactional
    public CheckoutBookResult processCheckout(CheckoutBookCommand command) throws BusinessException {
        logger.debug("CheckoutBookServiceImpl | processCheckout |");
        CheckoutBookResult result = EntityConverter.buildResult(command);

        BigDecimal discount = BigDecimal.ZERO;
        BigDecimal totalPrice = BigDecimal.ZERO;
        BigDecimal promoAmount = BigDecimal.ZERO;
        if (!CollectionUtils.isEmpty(discountRulesList) && discountRulesList.size() > 0) {
            for(DiscountRules discountRule: discountRulesList) {
                discount = discount.add(discountRule.apply(command.getBookCommands()));
            }
        }
        /*for(BookCommand bookCommand: command.getBookCommands()) {
            totalPrice = totalPrice.add(bookCommand.getPrice());
        }*/
        totalPrice = command.getBookCommands().stream()
                .collect(Collectors.mapping(BookCommand::getPrice,Collectors.reducing(BigDecimal.ZERO, BigDecimal::add)));

        //Apply promocode if applicable
        if (StringUtils.isNotEmpty(command.getPromotionCode())) {
            Long promo = PromotionCode.getCodeByValue(command.getPromotionCode());
            promoAmount = BigDecimal.valueOf(promo);

            //discount = discount.add(BigDecimal.valueOf(promo));
        }

        logger.debug("CheckoutBookServiceImpl | processCheckout | totalPrice-{}, discount-{}", totalPrice, discount);
        result.setPromotionAmount(promoAmount);
        result.setTotalCheckoutAmount(totalPrice);
        result.setTotalPayableAmount(totalPrice.subtract(discount).subtract(promoAmount));
        result.setDiscountAmount(discount);
        return result;
    }

}
