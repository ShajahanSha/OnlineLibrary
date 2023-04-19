package com.online.library.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.online.library.domain.cqrs.exception.ErrorCode;
import com.online.library.domain.cqrs.exception.ErrorItem;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Created by Shajahan.Shaik on 04/18/2023.
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StatusInfo extends StoreModel {

    private StatusInfo() {
        // for Jackson
    }

    public StatusInfo(String status) {
        setStatus(status);
    }

    public StatusInfo(String status, ErrorCode errorCode) {
        setStatus(status);
        setErrorCode(errorCode);
    }

    public StatusInfo(String status, ErrorCode errorCode, List<ErrorItem> errorList) {
        setStatus(status);
        setErrorCode(errorCode);
        setErrorList(errorList);
    }
    @ApiModelProperty(position = 1, value = "Status")
    private String status;
    private ErrorCode errorCode;

    private List<ErrorItem> errorList;
}
