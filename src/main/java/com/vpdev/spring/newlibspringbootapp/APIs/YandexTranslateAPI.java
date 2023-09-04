package com.vpdev.spring.newlibspringbootapp.APIs;

import com.vpdev.spring.newlibspringbootapp.models.YandexResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//@Component
//@PropertySource("classpath:yandexAPI.properties")
public class YandexTranslateAPI implements CommandLineRunner {
    //токен и диск задаются в yandexAPI.properties
    @Value("${IAMtoken}")
    private String IAMtoken;
    @Value("${folderId}")
    private String folderId;

    @Override
    public void run(String... args) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        System.out.println("введите текст для перевода");
        Scanner scanner = new Scanner(System.in);
        String sebtanceToTranslate = scanner.nextLine();
        String URL = "https://translate.api.cloud.yandex.net/translate/v2/translate";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("Authorization", "Bearer " + IAMtoken);
        Map<String, String> jsonData = new HashMap<>();
        jsonData.put("folderId", folderId);
        jsonData.put("texts", "[" + sebtanceToTranslate + "]");
        jsonData.put("targetLanguageCode", "en");
        HttpEntity<Map<String, String>> request = new HttpEntity<>(jsonData, httpHeaders);
        YandexResponse objectFromYandex = restTemplate.postForObject(URL, request, YandexResponse.class);
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&_перевод_&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        System.out.println("Перевод :" + objectFromYandex.getTranslations().get(0).getText());
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
    }
}
