package com.example.demo.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class RatingId implements Serializable {
    private Integer userId;
    private Integer itemId;
}
