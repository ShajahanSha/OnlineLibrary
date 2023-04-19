package com.online.library.domain.cqrs.query;

import com.online.library.domain.cqrs.exception.BusinessException;
import com.online.library.domain.cqrs.exception.ValidationFailureException;
import com.online.library.domain.cqrs.exception.ValidationManager;
import org.springframework.beans.factory.annotation.Autowired;

/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/

public abstract class QueryHandlerExecutor<T extends Query<R>, R> implements QueryHandler<T, R> {

    //private static final Logger logger = LoggerFactory.getLogger(QueryHandlerExecutor.class);

    @Autowired
    private ValidationManager<T> validationManager;

    public QueryHandlerExecutor() {
    }

    public R execute(T query) throws Exception {
        try {
            //this.validationManager.validate(query);
            R result = this.handle(query);
            return result;
        } catch (ValidationFailureException validationEx) {

            //logger.info("Query Validation Exception: " + validationEx.getMessage(), validationEx);
            throw validationEx;
        } catch (BusinessException businessEx) {
            //logger.info("Query Business Exception: " + businessEx.getMessage(), businessEx);
            throw businessEx;
        } catch (Exception ex) {
            //logger.error("Query Exception: " + ex.getMessage(), ex);
            throw ex;
        }
    }
}