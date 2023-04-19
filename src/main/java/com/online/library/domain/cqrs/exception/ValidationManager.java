package com.online.library.domain.cqrs.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ConstraintViolation;
import java.util.*;

@Component
public class ValidationManager<T> {

    public Set<ConstraintViolation<T>> constraintViolations;

    @Autowired(required = false)
    private LocalValidatorFactoryBean localValidatorFactoryBean;

    public void validate(T command) throws Exception {
        constraintViolations = localValidatorFactoryBean.validate(command);

        if (constraintViolations.size() > 0) {
            Set<ConstraintViolation<T>> cvs = this.constraintViolations;
            List<ErrorItem> errors = new ArrayList<ErrorItem>();

            for (Iterator<ConstraintViolation<T>> it = cvs.iterator(); it.hasNext(); ) {
                ConstraintViolation cv = it.next();
                errors.add(new ErrorItem(cv.getPropertyPath().toString(), cv.getMessageTemplate()));
            }

            Collections.sort(errors);

            ErrorResponse errorResult = new ErrorResponse("Failed validation, refer to error list for hints", errors, command.toString() + " command has failed", "");
            throw new ValidationFailureException(errorResult);
        }

    }

    public List<ErrorItem> validateWithResults(T command) {
        constraintViolations = localValidatorFactoryBean.validate(command);

        if (constraintViolations.size() > 0) {
            Set<ConstraintViolation<T>> cvs = this.constraintViolations;
            List<ErrorItem> errors = new ArrayList<ErrorItem>();

            for (Iterator<ConstraintViolation<T>> it = cvs.iterator(); it.hasNext(); ) {
                ConstraintViolation cv = it.next();
                errors.add(new ErrorItem(cv.getPropertyPath().toString(), cv.getMessageTemplate()));
            }

            Collections.sort(errors);

            return errors;

        }

        return new ArrayList<ErrorItem>();
    }

}