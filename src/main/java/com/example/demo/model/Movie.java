package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name="movies")
public class Movie {
    @Id
    //@GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "movie_id")
    private Integer movieId;

    @Column(name = "movie_title")
    private String movieTitle;

    @Column(name = "release_date")
    private String releaseDate;

    @Column(name = "video_release_date")
    private String videoReleaseDate;

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

    public Integer getReleaseYear() {
        return Integer.parseInt(StringUtils.right(releaseDate, 4));
    }

    public Iterable<String> getGenres() {
        List<String> genres = new ArrayList<>();
        if(unknown == 1)
            genres.add("unknown");
        if(action == 1)
            genres.add("action");
        if(adventure == 1)
            genres.add("adventure");
        if(animation == 1)
            genres.add("animation");
        if(childrens == 1)
            genres.add("childrens");
        if(comedy == 1)
            genres.add("comedy");
        if(crime == 1)
            genres.add("crime");
        if(documentary == 1)
            genres.add("documentary");
        if(drama == 1)
            genres.add("drama");
        if(fantasy == 1)
            genres.add("fantasy");
        if(filmNoir == 1)
            genres.add("film-noir");
        if(horror == 1)
            genres.add("horror");
        if(mystery == 1)
            genres.add("mystery");
        if(romance == 1)
            genres.add("romance");
        if(sciFi == 1)
            genres.add("sci-fi");
        if(musical == 1)
            genres.add("musical");
        if(thriller == 1)
            genres.add("thriller");
        if(war == 1)
            genres.add("war");
        if(western == 1)
            genres.add("western");

        return genres;
    }

}
