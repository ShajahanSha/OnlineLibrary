package com.online.library.domain.cqrs.command;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.online.library.domain.cqrs.result.BookResult;
import com.online.library.domain.cqrs.validator.ValidateBookCommand;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * Created by Shajahan.Shaik on 04/18/2023.
 */
@ValidateBookCommand
@ApiModel(value = "BookCommand")
@Getter
@Setter
@Builder
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookCommand implements Command<BookResult> {
    private long bookId;
    private String name;
    private String description;
    private String author;
    private String classification;
    private BigDecimal price;
    private String isbn;
    private String serviceType;

    public BookCommand() {

    }

    public BookCommand(long bookId, String name, String description, String author, String classification, BigDecimal price, String isbn, String serviceType) {
        this.bookId = bookId;
        this.name = name;
        this.description = description;
        this.author = author;
        this.classification = classification;
        this.price = price;
        this.isbn = isbn;
        this.serviceType = serviceType;
    }
}
