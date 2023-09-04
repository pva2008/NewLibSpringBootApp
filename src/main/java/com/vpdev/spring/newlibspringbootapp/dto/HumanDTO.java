package com.vpdev.spring.newlibspringbootapp.dto;


import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class HumanDTO {
    @NotEmpty(message = "Данное поле должно быть не пустое")
    @Size(min = 3, max = 20, message = "Значение должно быть в пределах 3-20")
    @Column(name = "fio")
    private String fio;

    @Min(value = 1900, message = "Год рождения должен быть больше чем 1900")
    @Column(name = "year_of_birth")
    private int yearOfBirth;

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
}
