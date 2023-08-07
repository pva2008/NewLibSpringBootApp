package com.vpdev.spring.newlibspringbootapp.dto;


import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class BookDTO {


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
    private int bookYear;


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

    public int getBookYear() {
        return bookYear;
    }

    public void setBookYear(int bookYear) {
        this.bookYear = bookYear;
    }
}
