package com.vpdev.spring.newlibspringbootapp.services;


import com.vpdev.spring.newlibspringbootapp.dto.BookWrapperRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestTemplateService {
    private final RestTemplate restTemplate;

    @Autowired
    public RestTemplateService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public BookWrapperRequest responseRestBook() {
        String URL = "http://localhost:8080/restbook";
        return restTemplate.getForObject(URL, BookWrapperRequest.class);
    }
}
