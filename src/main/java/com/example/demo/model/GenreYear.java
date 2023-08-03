package com.example.demo.model;

import lombok.Data;

@Data
public class GenreYear {
    private String genre;
    private Integer year;

    public GenreYear (String genre, Integer year) {
        this.genre = genre;
        this.year = year;
    }
}