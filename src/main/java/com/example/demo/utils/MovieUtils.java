package com.example.demo.utils;

import com.example.demo.repository.MovieRepository;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieUtils {

    public static String movieCountsToTopGenre(final List<Object[]> movieFrequencies, MovieRepository movieRepository) {
        /*
        Parameters:
            movieFrequencies : list of Objects containing movie_id and its corresponding count/frequency
            movieRepository : JpaRepository interface for Movie entity

        Returns:
            Genre with the highest count/frequency
         */

        Long topFreq = 0L;
        // Map genre key to its number of occurrences in movieFrequencies
        HashMap<String, Long> watchCount = new HashMap<>();

        movieFrequencies.forEach(row-> {
            final Integer id = (Integer) row[0];
            final Long freq = (Long) row[1];

            movieRepository.findById(id).get().getGenres()
                    .forEach(genre -> {
                        final Long genreFreq = watchCount.getOrDefault(genre, 0L) + freq;
                        watchCount.put(genre, genreFreq);
                    });
        });

        return Collections.max(watchCount.entrySet(), Map.Entry.comparingByValue()).getKey();
    }
}
