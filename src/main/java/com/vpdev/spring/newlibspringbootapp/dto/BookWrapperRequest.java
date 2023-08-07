package com.vpdev.spring.newlibspringbootapp.dto;

import java.util.List;

public class BookWrapperRequest {
    private List<BookDTO> books;

    public BookWrapperRequest() {
    }

    public BookWrapperRequest(List<BookDTO> books) {
        this.books = books;
    }

    public List<BookDTO> getBooks() {
        return books;
    }

    public void setBooks(List<BookDTO> books) {
        this.books = books;
    }
}
