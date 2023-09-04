package com.vpdev.spring.newlibspringbootapp.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Component
public class SpringConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    //запускает стартовую страницу на маке + добавляем запуск по планировщику
//    @Scheduled(fixedDelay = 2000)
//    @Scheduled(fixedDelayString = "${interval}")
//    @Scheduled(cron = "${interval-in-cron}")
    public static void openHomePage() throws IOException {
        String URL = "http://localhost:8081/books/restsavetodb";
        Runtime rt = Runtime.getRuntime();
        rt.exec("open " + URL);
    }
}
