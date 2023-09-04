package com.vpdev.spring.newlibspringbootapp.models;

import java.util.List;

public class YandexResponse {
    private List<Translations> translations;

    public List<Translations> getTranslations() {
        return translations;
    }

    public void setTranslations(List<Translations> translations) {
        this.translations = translations;
    }
}
