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

    public List<Human> findAll() {
        return humanRepository.findAll();
    }

    public Human findById(int id) {
        return humanRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Human human) {
        humanRepository.save(human);
    }

    @Transactional
    public void update(int id, Human human) {
        human.setHumanId(id);
        humanRepository.save(human);
    }

    @Transactional
    public void deleteById(int id) {
        humanRepository.deleteById(id);
    }

    public Optional<Human> getHumanByFio(String fio) {
        return humanRepository.findByFio(fio);
    }

    public List<Book> getBookByPersonId(int id) {
        Optional<Human> foundHuman = humanRepository.findById(id);
        if (foundHuman.isPresent()) {
            Hibernate.initialize(foundHuman.get().getBooks());
            foundHuman.get().getBooks().forEach(book ->
            {
                if (book.getTakenAt() != null) {
                    long diffInMillies = Math.abs(book.getTakenAt().getTime() - new Date().getTime());
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
