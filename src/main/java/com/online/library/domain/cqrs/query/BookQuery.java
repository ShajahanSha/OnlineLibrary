package com.online.library.domain.cqrs.query;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.online.library.domain.cqrs.result.BookResult;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * Created by Shajahan.Shaik on 04/18/2023.
 */
@ApiModel(value = "OtpCommand")
@Getter
@Setter
@Builder
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookQuery implements Query<BookResult> {
    private Long bookId;
    private String name;
    private String author;
    private String classification;
    private BigDecimal price;
    private String isbn;

    public BookQuery() {

    }

    public BookQuery(Long bookId, String name, String author, String classification, BigDecimal price, String isbn) {
        this.bookId = bookId;
        this.name = name;
        this.author = author;
        this.classification = classification;
        this.price = price;
        this.isbn = isbn;
    }
}
