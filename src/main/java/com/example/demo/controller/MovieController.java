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
        final Iterable<Integer> mostWatchedIds = ratingRepository.mostWatched(mostWatchedFrequency);
        return movieRepository.findAllById(mostWatchedIds);
    }

    // Top Movie by genre
    @GetMapping(path="/movies/top/genre")
    public @ResponseBody HashMap<String, String> getTopByGenre() {
        // Map genre to its corresponding top movie
        HashMap<String, String> results = new HashMap<>();
        final Iterable<Integer> highestRatedIds = ratingRepository.highestRated();

        for(Integer id: highestRatedIds) {
            final Movie movie = movieRepository.findById(id).get();

            final Iterable<String> genres = movie.getGenres();
            for(String genre: genres) {
                if(!results.containsKey(genre))
                    results.put(genre, movie.getMovieTitle());
            }

            if (results.size() == 19)
                break;
        }

        return results;
    }


    // Top Movie by Year
    @GetMapping(path="/movies/top/year")
    public @ResponseBody HashMap<Integer, String> getTopByYear() {
        // Map year to its corresponding top movie
        HashMap<Integer, String> results = new HashMap<>();
        final Iterable<Integer> highestRatedIds = ratingRepository.highestRated();

        for(Integer id: highestRatedIds) {
            final Movie movie = movieRepository.findById(id).get();

            final Integer year = movie.getReleaseYear();
            if(!results.containsKey(year))
                results.put(year, movie.getMovieTitle());
        }

        return results;
    }


    // Top Movie by genre and year
    @GetMapping(path="/movies/top/genreyear")
    public @ResponseBody HashMap<GenreYear, String> getTopByGenreAndYear() {
        // Map year and genre to its corresponding top movie
        HashMap<GenreYear, String> results = new HashMap<>();
        final Iterable<Integer> highestRatedIds = ratingRepository.highestRated();

        for(Integer id: highestRatedIds) {
            final Movie movie = movieRepository.findById(id).get();

            final Integer year = movie.getReleaseYear();
            final Iterable<String> genres = movie.getGenres();

            for(String genre: genres) {
                final GenreYear genreYear = new GenreYear(genre, year);
                if(!results.containsKey(genreYear))
                    results.put(genreYear, movie.getMovieTitle());
            }
        }

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


    /*
    @GetMapping(path="/movies/all")
    public @ResponseBody Iterable<Movie> getAllMovies() {
        return movieRepository.findAll();
    }
     */

}