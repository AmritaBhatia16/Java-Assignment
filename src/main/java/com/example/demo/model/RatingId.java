package com.example.demo.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Data
public class RatingId implements Serializable {
    private Integer userId;
    private Integer itemId;
}
