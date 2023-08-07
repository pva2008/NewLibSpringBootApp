package com.vpdev.spring.newlibspringbootapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

import static com.vpdev.spring.newlibspringbootapp.config.SpringConfig.openHomePage;

@SpringBootApplication
public class NewLibSpringBootAppApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(NewLibSpringBootAppApplication.class, args);
        openHomePage("http://localhost:8081/books/restsavetodb");
    }

}
