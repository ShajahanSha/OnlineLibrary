package com.online.library.api;

import com.online.library.domain.constants.enums.ServiceType;
import com.online.library.domain.cqrs.command.BookCommand;
import com.online.library.domain.cqrs.commandhandler.BookCommandHandler;
import com.online.library.domain.cqrs.commandhandler.CheckoutCommandHandler;
import com.online.library.domain.cqrs.commandhandler.FetchQueryHandler;
import com.online.library.domain.cqrs.query.BookQuery;
import com.online.library.domain.cqrs.result.BookResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Shajahan.Shaik on 04/18/2023.
 */

@RestController
@RequestMapping(value = "/api/v1/books")
public class OnlineLibraryController {
    private static final Logger logger = LoggerFactory.getLogger(OnlineLibraryController.class);
    private static final int PAGE_DEFAULT_SIZE = 10;
    private final FetchQueryHandler queryHandler;
    private final BookCommandHandler commandHandler;
    private final CheckoutCommandHandler checkoutHandler;

    public OnlineLibraryController(FetchQueryHandler queryHandler, BookCommandHandler commandHandler, CheckoutCommandHandler checkoutHandler) {
        this.queryHandler = queryHandler;
        this.commandHandler = commandHandler;
        this.checkoutHandler = checkoutHandler;
    }

    @GetMapping("/health")
    public String status() {
        return "Online Book Store API is up and running";
    }

    @ApiOperation(value = "fetchPlan", notes = "fetchPlan inquiry")
    @RequestMapping(value = "/getBook/{bookId}", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getBookId(@PathVariable("bookId") long bookId) throws Exception {
        BookResult result = queryHandler.handle(BookQuery.builder().bookId(bookId).build());
        return ResponseEntity.ok(result);
    }

    @ApiOperation(value = "Get book from online Online Bookstore")
    @GetMapping()
    public ResponseEntity<?> getBook(@RequestParam(value = "name", required = false) String name,
                                     @RequestParam(value = "author", required = false) String author,
                                     Pageable pageable) throws Exception{
        Page<BookResult> resultList = queryHandler.fetchData(BookQuery.builder().name(name).author(author).build(), pageable);
        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> addBook(@RequestBody BookCommand command) throws Exception{
        command.setServiceType(ServiceType.CREATE.getValue());
        BookResult result = commandHandler.handle(command);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<?> updateBook(@RequestBody BookCommand command) throws Exception{
        command.setServiceType(ServiceType.UPDATE.getValue());
        BookResult result = commandHandler.handle(command);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<?> deleteBook(@PathVariable("bookId") long bookId) throws Exception{
        BookCommand command = new BookCommand();
        command.setBookId(bookId);
        command.setServiceType(ServiceType.DELETE.getValue());
        BookResult result = commandHandler.handle(command);
        return new ResponseEntity<>(result, HttpStatus.NO_CONTENT);
    }
}
