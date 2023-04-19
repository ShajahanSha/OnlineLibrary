package com.online.library.domain.cqrs.result;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.online.library.domain.cqrs.exception.ErrorCode;
import com.online.library.domain.model.StatusInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;


/**
 * Created by Shajahan.Shaik on 04/18/2023.
 */

@Getter
@Setter
@Builder
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookResult extends BaseResult {
    private long bookId;
    private String name;
    private String description;
    private String author;
    private String classification;
    private BigDecimal price;
    private String isbn;
    private ErrorCode errorMessage;
    private StatusInfo statusInfo;

    public BookResult() {

    }

    public BookResult(long bookId, String name, String description, String author, String classification, BigDecimal price, String isbn, ErrorCode errorMessage, StatusInfo statusInfo) {
        this.bookId = bookId;
        this.name = name;
        this.description = description;
        this.author = author;
        this.classification = classification;
        this.price = price;
        this.isbn = isbn;
        this.errorMessage = errorMessage;
        this.statusInfo = statusInfo;
    }

    @JsonIgnore
    public BookResult filter() {

        return this;
    }

}
