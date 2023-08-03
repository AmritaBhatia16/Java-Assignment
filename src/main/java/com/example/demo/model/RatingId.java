package com.example.demo.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

//@Data
@Getter
@Setter
@Embeddable
public class RatingId implements Serializable {
    private Integer userId;
    private Integer itemId;

    public RatingId() {

    }

    public RatingId(Integer userId, Integer itemId) {
        this. userId = userId;
        this.itemId = itemId;
    }
}
