package com.vpdev.spring.newlibspringbootapp.models;

public class Translations {
    private String text;
    private String detectedLanguageCode;
    //и обязательно геттеры и сеттеры

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDetectedLanguageCode() {
        return detectedLanguageCode;
    }

    public void setDetectedLanguageCode(String detectedLanguageCode) {
        this.detectedLanguageCode = detectedLanguageCode;
    }
}
