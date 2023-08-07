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

    //    public List<Book> findAll() {  //index
//                                        //String SQL = "select * from book";
//        return booksRepository.findAll();//jdbcTemplate.query(SQL, new BeanPropertyRowMapper<>(Book.class));
//    }
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


    public Book findById(int id) { //show
        //String SQL = "SELECT * FROM book WHERE bookid=?";
        return booksRepository.findById(id).orElse(null);//jdbcTemplate.query(SQL, new Object[]{id}, new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }

    public List<Book> findByTitleStartingWith(String query) {

        return booksRepository.findByTitleStartingWith(query);
    }

    @Transactional
    public void save(Book book) { //save
        //String SQL = "INSERT INTO book(title,author,book_year) values(?,?,?)";
        booksRepository.save(book);//jdbcTemplate.update(SQL, book.getTitle(), book.getAuthor(), book.getBook_year());
    }

    @Transactional
    public void update(int id, Book book) {
        book.setBookId(id); //String SQL = "UPDATE book SET title=?,author=?,book_year=? WHERE bookid=?";
        book.setOwner(booksRepository.findById(id).get().getOwner()); // обновляем связь, что бы не потерялась при обновлении
        booksRepository.save(book);//jdbcTemplate.update(SQL, book.getTitle(), book.getAuthor(), book.getBook_year(), id);

    }

    @Transactional
    public void deleteById(int id) //delete
    {
        booksRepository.deleteById(id);//    jdbcTemplate.update("DELETE FROM Book WHERE bookid=?", id);
    }
//есть в humans
//    public List<Book> showHumanBooks(int id) {
//        return booksRepository.//jdbcTemplate.query("SELECT * FROM book WHERE humanid=?", new Object[]{id},
//                //new BeanPropertyRowMapper<>(Book.class));
//    }

    public Optional<Human> getBookOwner(int id) {
        // Здесь Hibernate.initialize() не нужен, так как владелец (сторона One) загружается не лениво
        return booksRepository.findById(id).map(Book::getOwner);
//        return jdbcTemplate.query("SELECT  human.* FROM Book Join Human" +
//                                " On Book.humanid = human.humanid WHERE Book.bookid = ?", new Object[]{id},
//                        new BeanPropertyRowMapper<>(Human.class))
//                .stream().findAny();

    }

    // Освобождает книгу (этот метод вызывается, когда человек возвращает книгу в библиотеку)
    @Transactional
    public void release(int id) {
        booksRepository.findById(id).ifPresent(book ->
        {
            book.setOwner(null);
            book.setTakenAt(null);
            //добавить про время
        });
        //jdbcTemplate.update("UPDATE Book SET humanid=NULL WHERE bookid=?", id);
    }

    // Назначает книгу человеку (этот метод вызывается, когда человек забирает книгу из библиотеки)
    @Transactional
    public void assign(int id, Human selectedHuman) {
        booksRepository.findById(id).ifPresent(book ->
        {
            book.setOwner(selectedHuman);
            book.setTakenAt(new Date()); // текущее время
            //добавить про время
        });//jdbcTemplate.update("UPDATE Book SET humanid=? WHERE bookid=?", selectedHuman.gethumanid(), id);
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
        for (Book book : enreachlist) {
            booksRepository.save(book);

        }

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
