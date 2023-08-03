package com.example.demo.utils;

import com.example.demo.model.Movie;
import com.example.demo.repository.MovieRepository;

import java.util.HashMap;
import java.util.List;

public class MovieUtils {

    public static String movieCountsToTopGenre(final List<Object[]> movieFrequencies, MovieRepository movieRepository) {
        /*
        Parameters:
            movieFrequencies : list of Objects containing movie_id and its corresponding count/frequency
            movieRepository : JpaRepository interface for Movie entity

        Returns:
            Genre with the highest count/frequency
         */

        String topGenre = null; Long topFreq = 0L;
        // Map genre key to its number of occurrences in movieFrequencies
        HashMap<String, Long> watchCount = new HashMap<>();

        for (Object[] row: movieFrequencies) {
            final Integer id = (Integer) row[0];
            final Long freq = (Long) row[1];

            final Movie movie = movieRepository.findById(id).get();

            final Iterable<String> genres = movie.getGenres();
            for (String genre : genres) {
                Long genreFreq = 0L;
                if (watchCount.containsKey(genre)) {
                    // freq = sum(freq, watchCount.get(genre));
                    genreFreq = watchCount.get(genre) + freq;
                    watchCount.put(genre, genreFreq);
                } else {
                    genreFreq = freq;
                    watchCount.put(genre, genreFreq);
                }

                if (genreFreq > topFreq) {
                    topFreq = genreFreq;
                    topGenre = genre;
                }

            }
        }
        // System.out.println(watchCount);
        return topGenre;
    }
}
