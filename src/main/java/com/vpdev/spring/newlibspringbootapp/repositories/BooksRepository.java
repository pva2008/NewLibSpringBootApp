package com.vpdev.spring.newlibspringbootapp.repositories;


import com.vpdev.spring.newlibspringbootapp.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {

    public List<Book> findByTitleStartingWith(String query);
}
