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

    RestTemplate restTemplate = new RestTemplate();

    //запускает стартовую страницу на маке
    public static void openHomePage(String URL) throws IOException {
        Runtime rt = Runtime.getRuntime();
        rt.exec("open " + URL);
    }
}
