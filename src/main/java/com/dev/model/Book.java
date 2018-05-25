package com.dev.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Book {
    @Id @GeneratedValue
    private Long id;

    @Column (length = 100)
    private String title;

    @Column (length = 200)
    private String descr;

    @Column (name ="no_of_pages")
    private int nPages;

    private Float cost;
    private String isbn;
    private Language lang;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Language getLang() {
        return lang;
    }

    public void setLang(Language lang) {
        this.lang = lang;
    }

    public Book(String isbn, String title, Float unitCost, Integer nbOfPages, Language language, String description) {
        this.isbn = isbn;
        this.title = title;
        this.cost = unitCost;
        this.nPages = nbOfPages;
        this.lang = language;
        this.descr = description;
    }

    public Book() {

    }
}
