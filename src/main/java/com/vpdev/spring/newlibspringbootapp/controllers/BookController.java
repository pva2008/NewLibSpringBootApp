package com.vpdev.spring.newlibspringbootapp.controllers;


import com.vpdev.spring.newlibspringbootapp.dto.BookDTO;
import com.vpdev.spring.newlibspringbootapp.dto.BookWrapperRequest;
import com.vpdev.spring.newlibspringbootapp.models.Book;
import com.vpdev.spring.newlibspringbootapp.services.BooksService;
import com.vpdev.spring.newlibspringbootapp.services.RestTemplateService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/books")
@Slf4j
public class BookController {
    private final BooksService booksService;
    private final ModelMapper modelMapper;
    private final RestTemplateService restTemplateService;

    @Autowired
    public BookController(BooksService booksService, ModelMapper modelMapper, RestTemplateService restTemplateService) {
        this.booksService = booksService;
        this.modelMapper = modelMapper;
        this.restTemplateService = restTemplateService;
    }

    @GetMapping("/rest")
    public String getAllFromOldLibAndShow(Model model) {
        List<Book> books = booksService.enreachBooks(convertListToBook(restTemplateService.responseRestBook()));
        model.addAttribute("FromOldLibList", books);
        return "books/show";
    }


    @GetMapping("/restsavetodb")
    public String getAndSaveToDBFromOldLib(Model model) {
        @Valid
        List<Book> books = convertListToBook(restTemplateService.responseRestBook());
        booksService.enreachAndAddBooksToDB(books);
        log.info("books-->> {}", books);
        model.addAttribute("FromOldLibList", booksService.findAll());
        return "books/show";
    }

    private Book convertToBook(BookDTO bookDTO) {
        return modelMapper.map(bookDTO, Book.class);
    }

    private List<Book> convertListToBook(BookWrapperRequest bookWrapperRequest) {
        return bookWrapperRequest.getBooks().stream()
                .map((bookDTO) -> modelMapper.map(bookDTO, Book.class)).toList();
    }

    private BookDTO convertToBookDTO(Book book) {
        return modelMapper.map(book, BookDTO.class);
    }
}
