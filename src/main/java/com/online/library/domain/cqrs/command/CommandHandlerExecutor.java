package com.online.library.domain.cqrs.command;

import com.online.library.domain.cqrs.exception.BusinessException;
import com.online.library.domain.cqrs.exception.ValidationFailureException;
import com.online.library.domain.cqrs.exception.ValidationManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class CommandHandlerExecutor<T extends Command<R>, R> implements CommandHandler<T, R> {

    private static final Logger logger = LoggerFactory.getLogger(CommandHandlerExecutor.class);

    @Autowired
    private ValidationManager<T> validationManager;

    private CommandEventHandler _commandEventHandler;

    public CommandHandlerExecutor() {

    }

    public CommandHandlerExecutor(CommandEventHandler _commandEventHandler) {
        this();
        this._commandEventHandler = _commandEventHandler;
    }

    public R execute(T command) throws Exception {

        try {
            R result = this.handle(command);
            if (_commandEventHandler != null) {
                _commandEventHandler.publish(result);
                result = (R) _commandEventHandler.filter(result);
            }

            return result;
        } catch (ValidationFailureException validationEx) {
            //logger.info("Command Validation Exception: " + validationEx.getMessage(), validationEx);
            throw validationEx;
        } catch (BusinessException businessEx) {
            logger.error("Business Exception: " + businessEx.getMessage(), businessEx);
            throw businessEx;
        } catch (Exception ex) {
            logger.error("Command Exception: " + ex.getMessage(), ex);
            throw ex;
        }

    }

}