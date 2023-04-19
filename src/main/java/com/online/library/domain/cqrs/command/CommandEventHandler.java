package com.online.library.domain.cqrs.command;

import com.online.library.domain.cqrs.result.BaseResult;

public interface CommandEventHandler<R> {
    void publish(R result) throws Exception;
    BaseResult filter(R result) throws Exception;
}
