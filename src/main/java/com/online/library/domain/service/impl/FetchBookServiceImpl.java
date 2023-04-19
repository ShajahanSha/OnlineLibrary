package com.online.library.domain.service.impl;

import com.online.library.domain.cqrs.exception.BusinessException;
import com.online.library.domain.cqrs.query.BookQuery;
import com.online.library.domain.cqrs.result.BookResult;
import com.online.library.domain.repos.bo.BookBO;

import com.online.library.domain.repos.jpa.BookRepository;
import com.online.library.domain.service.FetchBookService;
//import com.querydsl.core.types.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shajahan.Shaik on 04/18/2023.
 */

@Service("fetchBookService")
public class FetchBookServiceImpl implements FetchBookService {

    protected static final Logger logger = LoggerFactory.getLogger(FetchBookServiceImpl.class);
    //private final Gson gsonObj = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
    @Autowired
    private BookRepository bookRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public FetchBookServiceImpl() {
    }

    @Override
    public Page<BookResult> fetchData(BookQuery bookQuery, Pageable pageable) throws BusinessException {
        logger.debug("BookServiceImpl | fetchBooks |");
        List<BookResult> resultList = new ArrayList<>();
        Page<BookBO> bookBOS = null;
        //QBookBO qBookBO = QBookBO.bookBO;

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<BookBO> query = cb.createQuery(BookBO.class);
        Root<BookBO> book = query.from(BookBO.class);
        Path<String> path = null;
        List<Predicate> predicates = new ArrayList<>();
        if (StringUtils.isNotEmpty(bookQuery.getName())) {
            path = book.get("name");
            predicates.add(cb.equal(path, bookQuery.getName()));
        }
        if (StringUtils.isNotEmpty(bookQuery.getAuthor())) {
            path = book.get("author");
            predicates.add(cb.equal(path, bookQuery.getAuthor()));
        }
        if (StringUtils.isNotEmpty(bookQuery.getClassification())) {
            path = book.get("classification");
            predicates.add(cb.equal(path, bookQuery.getClassification()));
        }
        if (StringUtils.isNotEmpty(bookQuery.getIsbn())) {
            path = book.get("isbn");
            predicates.add(cb.equal(path, bookQuery.getIsbn()));
        }
        query.select(book)
                .where(cb.or(predicates.toArray(new Predicate[predicates.size()])));

        //bookRepository.findAll(query, pageable);

        List<BookBO> bos = entityManager.createQuery(query).getResultList();

        Page<BookBO> allProductsSortedByName = bookRepository.findAllWithPagination(pageable);

        /*Page<BookBO> bookBOS = getReceiveMoneyTransactions(query, pageable);
        if (null == bookBOS) {
            logger.info("ReceivemonPaymentProcessor | fetchReceiveMoney | Transactions not found");
            throw new BusinessException(new ErrorCode("NO_TRANSACTIONS", "No Data Found"));
        }
        logger.info("ReceivemonPaymentProcessor | fetchReceiveMoney | List Size-{}", bookBOS.getSize());
        for (BookBO bo : bookBOS) {
            BookResult result = prepareResult(bo);
            resultList.add(result);
        }*/
        return PageableExecutionUtils.getPage(resultList, pageable, () -> bookBOS.getTotalElements());
    }

    /*private Page<BookBO> getReceiveMoneyTransactions(BookQuery searchCriteria, Pageable pageable) {
        Predicate predicate = getAuthPredicate(searchCriteria);
        return bookRepository.findAll(predicate, pageable);
    }*/

    /*private Predicate getAuthPredicate(SubscriptionPlanDetails searchCriteria) {
        QBooBillingPlanBO qBillingPlanBO = QBillingPlanBO.billingPlanBO;
        BooleanExpression expression = null;
        if (!org.springframework.util.StringUtils.isEmpty(searchCriteria.getMerchantCode())) {
            expression = null == expression ? qBillingPlanBO.merchant.code.equalsIgnoreCase(searchCriteria.getMerchantCode())
                    : expression.and(qBillingPlanBO.merchant.code.equalsIgnoreCase(searchCriteria.getMerchantCode()));
        }
        if (!org.springframework.util.StringUtils.isEmpty(searchCriteria.getPlanName())) {
            expression = null == expression ? qBillingPlanBO.planName.equalsIgnoreCase(searchCriteria.getPlanName())
                    : expression.and(qBillingPlanBO.planName.equalsIgnoreCase(searchCriteria.getPlanName()));
        }
        if (!org.springframework.util.StringUtils.isEmpty(searchCriteria.getStatus())) {
            expression = null == expression ? qBillingPlanBO.status.equalsIgnoreCase(searchCriteria.getStatus())
                    : expression.and(qBillingPlanBO.status.equalsIgnoreCase(searchCriteria.getStatus()));
        }
        if (!org.springframework.util.StringUtils.isEmpty(searchCriteria.getIntervalUnit())) {
            //expression = expression.and(qBillingPlanBO.billingCycle.status.equalsIgnoreCase(searchCriteria.getIntervalUnit()));
        }
        if (!StringUtils.isEmpty(searchCriteria.getPricingSchemeType())) {
            //expression = expression.and(qBillingPlanBO.billingCycle.status.equalsIgnoreCase(searchCriteria.getIntervalUnit()));
        }
        return expression;
    }*/

    private BookResult mapResult(BookBO bo) {
        return BookResult.builder().bookId(bo.getId())
                .name(bo.getName())
                .description(bo.getDescription())
                .classification(bo.getClassification())
                .price(bo.getPrice())
                .isbn(bo.getIsbn())
                .author(bo.getAuthor())
                .build();
    }

}
