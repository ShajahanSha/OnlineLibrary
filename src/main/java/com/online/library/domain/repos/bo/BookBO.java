package com.online.library.domain.repos.bo;


import javax.persistence.*;
import java.math.BigDecimal;

/**
 * <p>
 * This class holding data about Subscription for payment/topup
 * </p>
 *
 * @author Shajahan Shaik
 */
@Entity
@Table(name = "BOOK")
public class BookBO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "AUTHOR")
    private String author;

    @Column(name = "CLASSIFICATION")
    private String classification;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "ISBN")
    private String isbn;

    public BookBO() {
    }

    public BookBO(long id, String name, String description, String author, String classification, BigDecimal price, String isbn) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.author = author;
        this.classification = classification;
        this.price = price;
        this.isbn = isbn;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "Books [id=" + id + ", name=" + name + ", desc=" + description + ", author=" + author + "]";
    }
}
