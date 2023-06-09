package com.online.library.api;

import com.online.library.domain.constants.enums.ServiceType;
import com.online.library.domain.cqrs.command.CheckoutBookCommand;
import com.online.library.domain.cqrs.commandhandler.CheckoutCommandHandler;
import com.online.library.domain.cqrs.result.CheckoutBookResult;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Shajahan.Shaik on 04/18/2023.
 */

@RestController
@RequestMapping(value = "/api/v1/checkout")
//@EnableWebMvc
public class CheckoutController {
    private static final Logger logger = LoggerFactory.getLogger(CheckoutController.class);
    private final CheckoutCommandHandler checkoutHandler;

    public CheckoutController(CheckoutCommandHandler checkoutHandler) {
        this.checkoutHandler = checkoutHandler;
    }

    @ApiOperation(value = "checkout books from Online library")
    @PostMapping("/orders")
    public ResponseEntity<CheckoutBookResult> checkout(@RequestBody CheckoutBookCommand command) throws Exception{
        logger.debug("CheckoutController | orders");
        command.setServiceType(ServiceType.CHECKOUT.getValue());
        CheckoutBookResult result = checkoutHandler.handle(command);
        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }

}
