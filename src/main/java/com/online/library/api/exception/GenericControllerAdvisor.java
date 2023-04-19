package com.online.library.api.exception;

import com.online.library.domain.constants.enums.TransactionStatus;
import com.online.library.domain.cqrs.exception.BusinessException;
import com.online.library.domain.cqrs.exception.ErrorCode;
import com.online.library.domain.model.StatusInfo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Created by Shajahan.Shaik on 04/18/2023.
 */

@ControllerAdvice
@Slf4j
public class GenericControllerAdvisor extends ResponseEntityExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GenericControllerAdvisor.class);

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorCode> handleException(Exception exception) {
        log.error("error occurred", exception);
        StatusInfo statusInfo = new StatusInfo(TransactionStatus.FAILURE.toString(), new ErrorCode("SOMETHING_WENT_WRONG", "Something went wrong, Please contact support team"));
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {BusinessException.class})
    public ResponseEntity<?> handleBusinessException(BusinessException exception) {
        log.error("error occurred", exception);
        StatusInfo statusInfo = new StatusInfo(TransactionStatus.FAILURE.toString(), exception.getErrorCode());
        return new ResponseEntity<>(statusInfo, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
