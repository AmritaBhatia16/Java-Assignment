package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@Entity
@Table(name="movies")
public class Movie {

    @Getter
    private static final Integer presentGenreCount = 19;

    @Id
    @Column(name = "movie_id")
    private Integer movieId;

    @Column(name = "movie_title")
    private String movieTitle;

    @Column(name = "release_date")
    private String releaseDate;

    @Column(name = "unknown")
    private Integer unknown;

    @Column(name = "action")
    private Integer action;

    @Column(name = "adventure")
    private Integer adventure;

    @Column(name = "animation")
    private Integer animation;

    @Column(name = "childrens")
    private Integer childrens;

    @Column(name = "comedy")
    private Integer comedy;

    @Column(name = "crime")
    private Integer crime;

    @Column(name = "documentary")
    private Integer documentary;

    @Column(name = "drama")
    private Integer drama;

    @Column(name = "fantasy")
    private Integer fantasy;

    @Column(name = "film-noir")
    private Integer filmNoir;

    @Column(name = "horror")
    private Integer horror;

    @Column(name = "mystery")
    private Integer mystery;

    @Column(name = "romance")
    private Integer romance;

    @Column(name = "sci-fi")
    private Integer sciFi;

    @Column(name = "musical")
    private Integer musical;

    @Column(name = "thriller")
    private Integer thriller;

    @Column(name = "war")
    private Integer war;

    @Column(name = "western")
    private Integer western;

    ArrayList<String> genres;

    public Integer getReleaseYear() {
        return Integer.parseInt(StringUtils.right(releaseDate, 4));
    }

    public Iterable<String> getGenres() {
        Map<String, Integer> genreFlags = new HashMap<>();
        genreFlags.put("unknown", unknown);
        genreFlags.put("action", action);
        genreFlags.put("adventure", adventure);
        genreFlags.put("animation", animation);
        genreFlags.put("childrens", childrens);
        genreFlags.put("comedy", comedy);
        genreFlags.put("crime", crime);
        genreFlags.put("documentary", documentary);
        genreFlags.put("drama", drama);
        genreFlags.put("fantasy", fantasy);
        genreFlags.put("film-noir", filmNoir);
        genreFlags.put("horror", horror);
        genreFlags.put("mystery", mystery);
        genreFlags.put("romance", romance);
        genreFlags.put("sci-fi", sciFi);
        genreFlags.put("musical", musical);
        genreFlags.put("thriller", thriller);
        genreFlags.put("war", war);
        genreFlags.put("western", western);

        List<String> genres = new ArrayList<>();
        genreFlags.forEach((genre, flag) -> {
            if (flag != null && flag == 1) {
                genres.add(genre);
            }
        });

        return genres;
    }

}
