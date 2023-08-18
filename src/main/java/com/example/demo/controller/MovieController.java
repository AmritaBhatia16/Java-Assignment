package com.example.demo.controller;

import com.example.demo.model.GenreYear;
import com.example.demo.model.Movie;
import com.example.demo.repository.RatingRepository;
import com.example.demo.repository.MovieRepository;
import com.example.demo.utils.MovieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(path="/demo")
public class MovieController {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private RatingRepository ratingRepository;


    // Most watched Movie
    @GetMapping(path="/movies/mostwatched")
    public @ResponseBody Iterable<Movie> getMostWatched() {
        final Integer mostWatchedFrequency = ratingRepository.highestMovieFrequency();
        final Iterable<Integer> mostWatchedIds = ratingRepository.findMovieIdsWithFrequency(mostWatchedFrequency);
        return movieRepository.findAllById(mostWatchedIds);
    }


    // Top Movie by genre
    @GetMapping(path="/movies/top/genre")
    public @ResponseBody HashMap<String, String> getTopByGenre() {
        // Map genre to its corresponding top movie
        HashMap<String, String> results = new HashMap<>();
        final Iterable<Integer> highestRatedIds = ratingRepository.highestRated();

        final boolean[] stopLoop = {false};

        highestRatedIds.forEach(id -> {
            if (!stopLoop[0]) {
                final Movie movie = movieRepository.findById(id).get();
                final Iterable<String> genres = movie.getGenres();
                genres.forEach(genre -> results.putIfAbsent(genre, movie.getMovieTitle()));

                if (results.size() == Movie.getPresentGenreCount()) {
                    stopLoop[0] = true;
                }
            }
        });

        return results;
    }


    // Top Movie by Year
    @GetMapping(path="/movies/top/year")
    public @ResponseBody HashMap<Integer, String> getTopByYear() {
        // Map year to its corresponding top movie
        HashMap<Integer, String> results = new HashMap<>();
        final Iterable<Integer> highestRatedIds = ratingRepository.highestRated();

        highestRatedIds.forEach(id -> {
            final Movie movie = movieRepository.findById(id).get();
            final Integer year = movie.getReleaseYear();
            results.putIfAbsent(year, movie.getMovieTitle());
        });

        return results;
    }


    // Top Movie by genre and year
    @GetMapping(path="/movies/top/genreyear")
    public @ResponseBody HashMap<GenreYear, String> getTopByGenreAndYear() {
        // Map year and genre to its corresponding top movie
        HashMap<GenreYear, String> results = new HashMap<>();
        final Iterable<Integer> highestRatedIds = ratingRepository.highestRated();

        highestRatedIds.forEach(id -> {
            final Movie movie = movieRepository.findById(id).get();

            final Integer year = movie.getReleaseYear();
            final Iterable<String> genres = movie.getGenres();

            genres.forEach(genre -> {
                final GenreYear genreYear = new GenreYear(genre, year);
                results.putIfAbsent(genreYear, movie.getMovieTitle());
            });
        });

        return results;
    }


    // Most watched genre
    @GetMapping(path="/genres/mostwatched")
    public @ResponseBody String getMostWatchedGenre() {
        final List<Object[]> movieFrequencies = ratingRepository.movieFrequencies();
        return MovieUtils.movieCountsToTopGenre(movieFrequencies, movieRepository);
    }


    // Highest rated genre
    @GetMapping(path="/genres/top")
    public @ResponseBody String getHighestRatedGenre() {
        final List<Object[]> highestRatedMovieFrequencies = ratingRepository.highestRatedMovieFrequencies();
        return MovieUtils.movieCountsToTopGenre(highestRatedMovieFrequencies, movieRepository);
    }

}