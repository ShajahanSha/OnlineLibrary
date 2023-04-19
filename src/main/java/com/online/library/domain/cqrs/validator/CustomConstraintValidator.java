package com.online.library.domain.cqrs.validator;

/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;
import java.util.regex.Pattern;


public abstract class CustomConstraintValidator<A extends Annotation, T> implements ConstraintValidator<A, T> {

    private ConstraintValidatorContext constraintValidatorContext;
    private boolean errorFlag;
    //private static final Logger LOGGER = LoggerFactory.getLogger(CustomConstraintValidator.class);

    @Override
    public boolean isValid(T validationObject, ConstraintValidatorContext constraintValidatorContext) {
        this.constraintValidatorContext = constraintValidatorContext;
        constraintValidatorContext.disableDefaultConstraintViolation();

        return validate(validationObject, constraintValidatorContext);
    }

    protected abstract boolean validate(T validationObject, ConstraintValidatorContext constraintValidatorContext);

    protected void addMessage(String message, String fieldName) {
        Assert.state(constraintValidatorContext != null, "constraintValidatorContext must not be null");
        constraintValidatorContext.buildConstraintViolationWithTemplate(message).addPropertyNode(fieldName).addConstraintViolation();
    }

    public boolean isErrorFlag() {
        return errorFlag;
    }

    public void setErrorFlag(boolean errorFlag) {
        this.errorFlag = errorFlag;
    }

    public void mandatoryCheck(String fieldName, String fieldValue, String message) {
        StringBuilder error = new StringBuilder(StringUtils.isEmpty(message) ? ValidationMessage.ERROR_REQUIRED : message);
        if (StringUtils.isEmpty(fieldValue)) {
            addMessage(error.toString(), fieldName);
            setErrorFlag(false);
        }
    }

    public void regExpressionCheck(String fieldName, String fieldValue, String regExpression, String message, boolean showRegExpression) {
        StringBuilder error = new StringBuilder(StringUtils.isEmpty(message) ? ValidationMessage.ERROR_INVALID_FORMAT : message);
        //LOGGER.debug("CustomConstraintValidator | regExpressionCheck | fieldName-{} fieldValue-{}", fieldName, fieldValue);
        if (!StringUtils.isEmpty(fieldValue) && !Pattern.matches(regExpression, fieldValue)) {
            if (showRegExpression) {
                error = error.append(", format should be ").append(regExpression);
            }
            addMessage(error.toString(), fieldName);
            setErrorFlag(false);
        }
    }
}
