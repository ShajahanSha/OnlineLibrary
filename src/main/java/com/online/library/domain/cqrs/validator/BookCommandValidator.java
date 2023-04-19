package com.online.library.domain.cqrs.validator;

import com.online.library.domain.cqrs.command.BookCommand;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidatorContext;

@Component
public class BookCommandValidator extends CustomConstraintValidator<ValidateBookCommand, BookCommand> {

    @Override
    public void initialize(ValidateBookCommand validatePaymentCommand) {

    }

    @Override
    protected boolean validate(BookCommand bookCommand, ConstraintValidatorContext constraintValidatorContext) {

        /*if (!Pattern.matches("[A-Za-z0-9\\s]", paymentCommand.getMerchantInfo().getMerchantBatchId())) {
            addMessage(String.format(ValidationMessage.ERROR_CHAR_LENGTH_BETWEEN, 1, 50), "surname");
            return false;
        }*/
        return true;
    }



}
