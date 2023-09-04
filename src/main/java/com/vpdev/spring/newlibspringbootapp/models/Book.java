package com.vpdev.spring.newlibspringbootapp.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Date;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;

    @NotEmpty(message = "Название книги не должно быть пустым")
    @Size(min = 2, max = 200, message = " название книги должно быть от 2 до 200")
    @Column(name = "title")
    private String title;

    @NotEmpty(message = "Автор  не должен быть пустым")
    @Size(min = 2, max = 100, message = " название автора книги должно быть от 2 до 100")
    @Column(name = "author")
    private String author;

    @Min(value = 1500, message = "Год  должен быть больше чем 1500")
    @Column(name = "book_year")
    private Integer bookYear;

    @ManyToOne
    @JoinColumn(name = "human_id", referencedColumnName = "human_id")
    private Human owner;

    @Column(name = "taken_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date takenAt;

    public Book() {
    }

    public Book(String title, String author, Integer bookYear) {
        this.title = title;
        this.author = author;
        this.bookYear = bookYear;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getBookYear() {
        return bookYear;
    }

    public void setBookYear(Integer bookYear) {
        this.bookYear = bookYear;
    }

    public Human getOwner() {
        return owner;
    }

    public void setOwner(Human owner) {
        this.owner = owner;
    }

    public Date getTakenAt() {
        return takenAt;
    }

    public void setTakenAt(Date takenAt) {
        this.takenAt = takenAt;
    }
}
