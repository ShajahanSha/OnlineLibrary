package com.online.library.domain.cqrs.validator;

import com.online.library.domain.cqrs.exception.BusinessException;

public class CustomException extends BusinessException {
    public CustomException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }
}
