package com.vpdev.spring.newlibspringbootapp.APIs;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class ConsumerRegres {


    public static void regresApi() {
        String URL = "https://reqres.in/api/users/3";
        String URL2 = "https://reqres.in/api/users";
        Map<String, String> mapToSend = new HashMap<>();
        RestTemplate restTemplate = new RestTemplate();

        mapToSend.put("name", "Valera");
        mapToSend.put("job", "data engineer");
        //для отправки мапы по сети мы должны ее упаковать в HttpEntity
        HttpEntity<Map<String, String>> request = new HttpEntity<>(mapToSend);
        String string = restTemplate.postForObject(URL2, request, String.class);
        String response = restTemplate.getForObject(URL, String.class);

        System.out.println("*******************reqres.in************************");
        System.out.println(string);
        System.out.println(response);
        System.out.println("****************************************************");
        log.info("*******************reqres.in************************");
        log.info(string);
        log.info(response);
        log.info("****************************************************");

    }

}





