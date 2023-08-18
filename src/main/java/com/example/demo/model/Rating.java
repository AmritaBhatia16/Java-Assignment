package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "ratings")
@IdClass(RatingId.class)
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
