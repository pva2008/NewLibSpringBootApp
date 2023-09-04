package com.vpdev.spring.newlibspringbootapp.services;


import com.vpdev.spring.newlibspringbootapp.models.Book;
import com.vpdev.spring.newlibspringbootapp.models.Human;
import com.vpdev.spring.newlibspringbootapp.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {
    BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }
    public List<Book> findWithPagination(Integer page, Integer booksPerPage, boolean sortByYear) {
        if (sortByYear)
            return booksRepository.findAll(PageRequest.of(page, booksPerPage, Sort.by("bookYear"))).getContent();
        else
            return booksRepository.findAll(PageRequest.of(page, booksPerPage)).getContent();
    }

    public List<Book> findAll(boolean sortByYear) {
        if (sortByYear)
            return booksRepository.findAll(Sort.by("bookYear"));
        else
            return booksRepository.findAll();
    }

    public List<Book> findAll() {
        return booksRepository.findAll();
    }

    public Book findById(int id) {
        return booksRepository.findById(id).orElse(null);
    }

    public List<Book> findByTitleStartingWith(String query) {
        return booksRepository.findByTitleStartingWith(query);
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void update(int id, Book book) {
        book.setBookId(id);
        book.setOwner(booksRepository.findById(id).get().getOwner());
        booksRepository.save(book);
    }

    @Transactional
    public void deleteById(int id) {
        booksRepository.deleteById(id);
    }
    public Optional<Human> getBookOwner(int id) {
        return booksRepository.findById(id).map(Book::getOwner);
    }

    @Transactional
    public void release(int id) {
        booksRepository.findById(id).ifPresent(book ->
        {
            book.setOwner(null);
            book.setTakenAt(null);
        });
    }

    @Transactional
    public void assign(int id, Human selectedHuman) {
        booksRepository.findById(id).ifPresent(book ->
        {
            book.setOwner(selectedHuman);
            book.setTakenAt(new Date());
        });
    }

    @Transactional
    public void addBook(Book bookToAdd) {
        booksRepository.save(bookToAdd);
    }

    @Transactional
    public List<Book> enreachBooks(List<Book> bookListFromOldLibrary) {
        List<Book> enreachlist = bookListFromOldLibrary.stream().map((old) -> {
            old.setTakenAt(new Date());
            old.setTitle(old.getTitle() + "__________________Join_ToNewLib_mark");
            return old;
        }).toList();
//        for (Book book : enreachlist) {
//            booksRepository.save(book);
//        }
        return enreachlist;
    }

    @Transactional
    public void enreachAndAddBooksToDB(List<Book> bookListFromOldLibrary) {
        List<Book> enreachlist = bookListFromOldLibrary.stream().map((old) -> {
            old.setTakenAt(new Date());
            old.setTitle(old.getTitle() + "__________________Join_ToNewLib_mark");
            return old;
        }).toList();
        for (Book book : enreachlist) {
            booksRepository.save(book);
        }
    }
}
