package com.online.library.domain.cqrs.result;

import com.online.library.domain.model.StatusInfo;

public interface ModuleResult {

    void setStatusInfo(StatusInfo statusInfo);

}
