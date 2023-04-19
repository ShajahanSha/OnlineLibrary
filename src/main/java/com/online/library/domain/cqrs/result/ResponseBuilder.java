package com.online.library.domain.cqrs.result;

import com.online.library.domain.cqrs.exception.BusinessException;
import com.online.library.domain.cqrs.exception.ErrorCode;
import com.online.library.domain.cqrs.exception.ErrorResponse;
import com.online.library.domain.cqrs.exception.ValidationFailureException;
import com.online.library.domain.model.StatusInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessageHandlingException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionSystemException;

/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/

/**
 * Created by Shajahan.Shaik on 04/18/2023.
 */
@Component
public class ResponseBuilder {
    //private static final Logger logger = LoggerFactory.getLogger(ResponseBuilder.class);

    public static ResponseEntity.BodyBuilder buildFailureResponse(StatusInfo statusInfo, Exception exp) {
        //logger.debug("Error details while calling the subscription API, message -{}, detailedMessage -{}", exp.getMessage(), exp);
        ResponseEntity.BodyBuilder rspBodyBuilder = null;
        if (exp instanceof BusinessException) {
            statusInfo.setErrorCode(((BusinessException) exp).getErrorCode());
            rspBodyBuilder = ResponseEntity.status(HttpStatus.BAD_REQUEST);
        } else if (exp instanceof ValidationFailureException) {
            ErrorResponse errorResponse = ((ValidationFailureException) exp).getErrorResponse();
            statusInfo.setErrorCode(new ErrorCode("MANDATORY_AND_FORMAT_ERROR", errorResponse.getMessage()));
            statusInfo.setErrorList(errorResponse.getErrors());
            rspBodyBuilder = ResponseEntity.status(HttpStatus.BAD_REQUEST);
        } else if (exp instanceof MessageHandlingException && exp.getCause() instanceof BusinessException) {
            statusInfo.setErrorCode(((BusinessException) exp.getCause()).getErrorCode());
            rspBodyBuilder = ResponseEntity.status(HttpStatus.BAD_REQUEST);
        } else if (exp instanceof TransactionSystemException) {
            statusInfo.setErrorCode(((BusinessException) ((TransactionSystemException) exp).getApplicationException()).getErrorCode());
            rspBodyBuilder = ResponseEntity.status(HttpStatus.BAD_REQUEST);
        } else {
            statusInfo.setErrorCode(new ErrorCode("SYSTEM_ERROR", "System Error, Please contact noqodi"));
            rspBodyBuilder = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //logger.error("Error Code=" + statusInfo.getErrorCode().getCode() + " Error Message=" + statusInfo.getErrorCode().getMessage());
        return rspBodyBuilder;
    }

}
