package com.online.library.domain.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchCriteria {
    private String name;
    private String author;
    private String classification;
    private long bookId;

    public SearchCriteria(String name, String author, long bookId) {
        super();
        this.name = name;
        this.author = author;
        this.bookId = bookId;
    }
}
