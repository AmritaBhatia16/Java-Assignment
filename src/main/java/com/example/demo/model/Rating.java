package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ratings")
//@IdClass(Data.class)
@IdClass(RatingId.class)
//implements Serializable
public class Rating {
    @Id
    @Column(name = "user_id")
    private Integer userId;

    @Id
    @Column(name = "item_id")
    private Integer itemId;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "timestamp")
    private String timestamp;
}
