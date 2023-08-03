package com.example.demo.repository;

import com.example.demo.model.Rating;
import com.example.demo.model.RatingId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, RatingId> {

    @Query(nativeQuery = true, value = "SELECT user_id FROM ratings GROUP BY user_id ORDER BY count(*) DESC LIMIT 1")
    Integer mostActiveUser();

    @Query(nativeQuery = true, value = "SELECT count(*) FROM ratings GROUP BY item_id ORDER BY count(*) DESC LIMIT 1")
    Integer highestMovieFrequency();

    @Query(nativeQuery = true, value = "SELECT item_id from ratings GROUP BY item_id HAVING count(item_id) = :freq")
    Iterable<Integer> mostWatched(Integer freq);

    @Query(nativeQuery = true, value = "select item_id from ratings where rating = 5\n" +
            "group by item_id order by count(rating) DESC;")
    Iterable<Integer> highestRated();

    @Query(nativeQuery = true, value = "select item_id, count(item_id) from ratings group by item_id")
    // + "order by count(item_id) DESC" for using early stopping greedy approach
    List<Object[]> movieFrequencies();

    @Query(nativeQuery = true, value = "select item_id, count(item_id) from ratings where rating = 5 group by item_id")
    // + "order by count(item_id) DESC" for using early stopping greedy approach
    List<Object[]> highestRatedMovieFrequencies();
}
