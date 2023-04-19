package com.online.library.domain.cqrs.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = BookCommandValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateBookCommand {
    String message() default "Invalid Book Request";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
