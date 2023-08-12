package com.vpdev.spring.newlibspringbootapp.APIs;

import com.vpdev.spring.newlibspringbootapp.models.YandexResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
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
        //Для помещения заголовков в запрос создадим объект HttpHeaders(import org.springframework.http.HttpHeaders;)
        HttpHeaders httpHeaders = new HttpHeaders();
        //зарезервированный заголовок
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        //кастомный в формате ключ,Bearer+пробел+значение
        httpHeaders.add("Authorization", "Bearer " +
                IAMtoken);
        //создаем мапу которая потом в JSON преобразится
        Map<String, String> jsonData = new HashMap<>();
        jsonData.put("folderId", folderId);
        //"texts": ["Hello", "World"], для обозначения массива элементов в JSON
        jsonData.put("texts", "[" + sebtanceToTranslate + "]");
        jsonData.put("targetLanguageCode", "en");

        //request в конструктор ложим нашу мапу с данными и заголовки
        HttpEntity<Map<String, String>> request = new HttpEntity<>(jsonData, httpHeaders);

        // String stringFromYandex = restTemplate.postForObject(URL,request,String.class);
        //Для парсинга не в строку а в класс и соответственно парсить его не надо а можно через геттеры просто вывести
        YandexResponse objectFromYandex = restTemplate.postForObject(URL, request, YandexResponse.class);

//        //так мы получим строчку формате JSON и ее нужно распарсить
//        ObjectMapper objectMapper = new ObjectMapper();
//        //objectMapper возвратит jsonNode с распаршенными ключами
//        JsonNode jsonNode = objectMapper.readTree(stringFromYandex); // выбрасывает throws JsonProcessingException {
//        //и так как нам приходит в ответ массив переводов то выводим его
//        System.out.println("Перевод :" + jsonNode.get("Translations").get(0).get("text"));
//        //Но что б каждый раз не парсить есть ответ можно создать собственный класс для распарсивания
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&_перевод_&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");

        System.out.println("Перевод :" + objectFromYandex.getTranslations().get(0).getText());
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");


    }


}
