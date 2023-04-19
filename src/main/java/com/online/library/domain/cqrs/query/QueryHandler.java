package com.online.library.domain.cqrs.query;

public interface QueryHandler<T extends Query<R>, R> {

    R handle(T command) throws Exception;
}
