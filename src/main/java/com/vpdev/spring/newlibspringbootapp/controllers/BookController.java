package com.vpdev.spring.newlibspringbootapp.controllers;


import com.vpdev.spring.newlibspringbootapp.dto.BookDTO;
import com.vpdev.spring.newlibspringbootapp.dto.BookWrapperRequest;
import com.vpdev.spring.newlibspringbootapp.models.Book;
import com.vpdev.spring.newlibspringbootapp.services.BooksService;
import com.vpdev.spring.newlibspringbootapp.services.HumanService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/books")
@Slf4j
public class BookController {

    private final BooksService booksService;
    private final HumanService humanService;
    private final RestTemplate restTemplate;
    private final ModelMapper modelMapper;

    @Autowired
    public BookController(BooksService booksService, HumanService humanService, RestTemplate restTemplate, ModelMapper modelMapper) {
        this.booksService = booksService;
        this.humanService = humanService;
        this.restTemplate = restTemplate;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/rest")
    public String getAllFromOldLibAndShow(Model model) {
        String URL = "http://localhost:8080/restbook";
        BookWrapperRequest response = restTemplate.getForObject(URL, BookWrapperRequest.class);
        List<Book> books = convertListToBook(response);
        books = booksService.enreachBooks(books);
        model.addAttribute("FromOldLibList", books);
        log.info("books /rest-->> {}", books);
        return "books/show";
    }
//разобраться с валидацией принимаемых данных и ее обработкой !!

    @GetMapping("/restsavetodb")
    public String getAndSaveToDBFromOldLib(Model model) {
        String URL = "http://localhost:8080/restbook";
        BookWrapperRequest response = restTemplate.getForObject(URL, BookWrapperRequest.class);
        @Valid
        List<Book> books = convertListToBook(response);
        booksService.enreachAndAddBooksToDB(books);
        log.info("books-->> {}", books);

        model.addAttribute("FromOldLibList", booksService.findAll());

        return "books/show";
    }

    //https://www.baeldung.com/spring-rest-template-error-handling
    //добавить обработчик
//    @ExceptionHandler
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ErrorResponse handleException(MethodArgumentNotValidException exception) {
//        //....
//        return ErrorResponse.builder().message(errorMsg).build();
//    }

    //добавить Batch_update

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
