package com.vpdev.spring.newlibspringbootapp.services;


import com.vpdev.spring.newlibspringbootapp.models.Book;
import com.vpdev.spring.newlibspringbootapp.models.Human;
import com.vpdev.spring.newlibspringbootapp.repositories.HumanRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class HumanService {
    HumanRepository humanRepository;


    @Autowired

    public HumanService(HumanRepository humanRepository) {
        this.humanRepository = humanRepository;


    }


    public List<Human> findAll() {          //index
        // String SQL = "select * from human";
        //humanRepository.
        //entityManager.clear();
        //entityManager.unwrap(Session.class).getSessionFactory().getCache().containsEntity()

        return humanRepository.findAll();

    }

    public Human findById(int id) {     //show
        // String SQL = "SELECT * FROM human WHERE humanid=?";
        return humanRepository.findById(id).orElse(null);  //jdbcTemplate.query(SQL, new Object[]{id}, new BeanPropertyRowMapper<>(Human.class)).stream().findAny().orElse(null);
    }

    @Transactional
    public void save(Human human) {         //save
        humanRepository.save(human);         // String SQL = "INSERT INTO human( fio,year_of_birth) values(?,?)";
        //jdbcTemplate.update(SQL, human.getFio(), human.getYear_of_birth());
    }

    @Transactional
    public void update(int id, Human human) { //update
        //в SD JPA нет отдельного save, если мы будем в save осхранять объект который есть в БД но отличается - то объект как тут и надо
        // просто обновится в БД с новыми полями
        human.setHumanId(id);// компилятор ругается на humanRepositiry.save(human.sethumanid(id)); - не понятно???
        humanRepository.save(human);//        String SQL = "UPDATE human SET fio=?,year_of_birth=? WHERE humanid=?";
        //        jdbcTemplate.update(SQL, human.getFio(), human.getYear_of_birth(), id);

    }

    @Transactional
    public void deleteById(int id) {     //delete
        humanRepository.deleteById(id);  //jdbcTemplate.update("DELETE FROM human WHERE humanid=?", id);
    }

    // для валидатора
    public Optional<Human> getHumanByFio(String fio) {

        return humanRepository.findByFio(fio);
        // jdbcTemplate.query("select FROM human WHERE humanid=?",
        //new Object[]{fio}, new BeanPropertyRowMapper<>(Human.class)).stream().findAny();
    }


    public List<Book> getBookByPersonId(int id) {
        Optional<Human> foundHuman = humanRepository.findById(id);     //jdbcTemplate.query("select FROM book WHERE humanid=?",
        if (foundHuman.isPresent()) {                                     // new Object[]{id}, new BeanPropertyRowMapper<>(Book.class));
            Hibernate.initialize(foundHuman.get().getBooks());
            foundHuman.get().getBooks().forEach(book -> {
                if (book.getTakenAt() != null) {
                    long diffInMillies = Math.abs(book.getTakenAt().getTime() - new Date().getTime());
                    // 864000000 милисекунд = 10 суток
                    if (diffInMillies > 864000000) {
                        //  book.setExpired(true);// книга просрочена
                    }
                }
            });

            return foundHuman.get().getBooks();
        } else {
            return Collections.emptyList();
        }

    }

}
