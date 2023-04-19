package com.online.library.domain.cqrs.command;

public interface CommandHandler<T extends Command<R>, R> {

    R handle(T command) throws Exception;
}

