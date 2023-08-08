package com.vpdev.spring.newlibspringbootapp.models;

import java.util.List;

public class YandexResponse {
    //те этот клас будет соответствовать json объекту, которым ответит нам сервер,
    //но тк Яндекс отвечает массивом то создадим класс а тут List из классов

    private List<Translations> translations;

    public List<Translations> getTranslations() {
        return translations;
    }

    public void setTranslations(List<Translations> translations) {
        this.translations = translations;
    }
}
