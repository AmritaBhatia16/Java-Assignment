package com.example.demo.controller;

import com.example.demo.model.GenreYear;
import com.example.demo.model.Movie;
import com.example.demo.repository.MovieRepository;
import com.example.demo.repository.RatingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class MovieControllerTests {

    @InjectMocks
    private MovieController movieController;

    @Mock
    private RatingRepository ratingRepository;

    @Mock
    private MovieRepository movieRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetMostWatched() {

        final Integer frequency = 10, movie1Id = 1, movie2Id = 2;

        when(ratingRepository.highestMovieFrequency()).thenReturn(frequency);
        when(ratingRepository.findMovieIdsWithFrequency(10)).thenReturn(List.of(movie1Id, movie2Id));

        when(movieRepository.findAllById(List.of(movie1Id, movie2Id))).thenReturn(List.of(new Movie(), new Movie()));

        Iterable<Movie> result = movieController.getMostWatched();
        assertTrue(result.iterator().hasNext());
    }


    @Test
    public void testGetTopByGenre() {

        final Integer movie1Id = 1, movie2Id = 2, movie3Id = 3;
        final String movie1Title = "Movie 1", movie2Title = "Movie 2", movie3Title = "Movie 3";
        final Integer genreTrue = 1;

        Movie movie1 = new Movie();
        movie1.setMovieTitle(movie1Title);
        movie1.setAnimation(genreTrue);

        Movie movie2 = new Movie();
        movie2.setMovieTitle(movie2Title);
        movie2.setComedy(genreTrue);

        Movie movie3 = new Movie();
        movie3.setMovieTitle(movie3Title);
        movie3.setComedy(genreTrue);

        when(ratingRepository.highestRated()).thenReturn(List.of(movie1Id, movie2Id, movie3Id));

        when(movieRepository.findById(movie1Id)).thenReturn(Optional.of(movie1));
        when(movieRepository.findById(movie2Id)).thenReturn(Optional.of(movie2));
        when(movieRepository.findById(movie3Id)).thenReturn(Optional.of(movie3));

        HashMap<String, String> result = movieController.getTopByGenre();

        assertEquals(2, result.size());
        assertEquals(movie1Title, result.get("animation"));
        assertEquals(movie2Title, result.get("comedy"));

    }


    @Test
    void testGetTopByYear() {

        final Integer movie1Id = 1;
        final String movie1Title = "Movie 1", movie1ReleaseDate = "01-01-2000";
        final Integer movie2Id = 2;
        final String movie2Title = "Movie 2", movie2ReleaseDate = "01-01-2000";

        Movie movie1 = new Movie();
        movie1.setMovieId(movie1Id);
        movie1.setMovieTitle(movie1Title);
        movie1.setReleaseDate(movie1ReleaseDate);

        Movie movie2 = new Movie();
        movie2.setMovieId(movie2Id);
        movie2.setMovieTitle(movie2Title);
        movie2.setReleaseDate(movie2ReleaseDate);

        when(ratingRepository.highestRated()).thenReturn(List.of(movie1Id, movie2Id));

        when(movieRepository.findById(movie1Id)).thenReturn(Optional.of(movie1));
        when(movieRepository.findById(movie2Id)).thenReturn(Optional.of(movie2));

        HashMap<Integer, String> result = movieController.getTopByYear();

        final Integer size = 1, movieYear = 2000;
        assertEquals(size, result.size());
        assertEquals(movie1Title, result.get(movieYear));
    }


    @Test
    void testGetTopByGenreAndYear() {

        final Integer movie1Id = 1;
        final String movie1Title = "Movie 1", movie1ReleaseDate = "01-01-2000";
        final Integer movie2Id = 2;
        final String movie2Title = "Movie 2", movie2ReleaseDate = "01-01-2005";
        final Integer genreTrue = 1;

        Movie movie1 = new Movie();
        movie1.setMovieTitle(movie1Title);
        movie1.setReleaseDate(movie1ReleaseDate);
        movie1.setAction(genreTrue);

        Movie movie2 = new Movie();
        movie2.setMovieTitle(movie2Title);
        movie2.setReleaseDate(movie2ReleaseDate);
        movie2.setDrama(genreTrue);

        when(ratingRepository.highestRated()).thenReturn(List.of(movie1Id, movie2Id));

        when(movieRepository.findById(movie1Id)).thenReturn(Optional.of(movie1));
        when(movieRepository.findById(movie2Id)).thenReturn(Optional.of(movie2));

        HashMap<GenreYear, String> result = movieController.getTopByGenreAndYear();
        assertEquals(2, result.size());
        assertEquals(movie1Title, result.get(new GenreYear("action", 2000)));
        assertEquals(movie2Title, result.get(new GenreYear("drama", 2005)));

    }

    @Test
    void testGetMostWatchedGenre() {

        final Integer movie1Id = 1, movie2Id = 2;
        final Long movie1Freq = 10L, movie2Freq = 20L;
        final Integer genreTrue = 1;

        Movie movie1 = new Movie();
        movie1.setMovieId(movie1Id);
        movie1.setAnimation(genreTrue);

        Movie movie2 = new Movie();
        movie2.setMovieId(movie2Id);
        movie2.setComedy(genreTrue);

        List<Object[]> movieFrequencies = new ArrayList<>();
        movieFrequencies.add(new Object[] {movie1Id, movie1Freq});
        movieFrequencies.add(new Object[] {movie2Id, movie2Freq});
        when(ratingRepository.movieFrequencies()).thenReturn(movieFrequencies);

        when(movieRepository.findById(movie1Id)).thenReturn(Optional.of(movie1));
        when(movieRepository.findById(movie2Id)).thenReturn(Optional.of(movie2));

        String result = movieController.getMostWatchedGenre();
        assertEquals("comedy", result);
    }


    @Test
    void testGetHighestRatedGenre() {

        final Integer movie1Id = 1, movie2Id = 2;
        final Long movie1Freq = 10L, movie2Freq = 20L;
        final Integer genreTrue = 1;

        Movie movie1 = new Movie();
        movie1.setMovieId(movie1Id);
        movie1.setAnimation(genreTrue);

        Movie movie2 = new Movie();
        movie2.setMovieId(movie2Id);
        movie2.setComedy(genreTrue);

        List<Object[]> highestRatedMovieFrequencies = new ArrayList<>();
        highestRatedMovieFrequencies.add(new Object[] {movie1Id, movie1Freq});
        highestRatedMovieFrequencies.add(new Object[] {movie2Id, movie2Freq});
        when(ratingRepository.highestRatedMovieFrequencies()).thenReturn(highestRatedMovieFrequencies);

        when(movieRepository.findById(movie1Id)).thenReturn(Optional.of(movie1));
        when(movieRepository.findById(movie2Id)).thenReturn(Optional.of(movie2));

        String result = movieController.getHighestRatedGenre();
        assertEquals("comedy", result);
    }

}
