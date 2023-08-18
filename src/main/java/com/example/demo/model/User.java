package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "age")
    private Integer age;

    @Column(name = "gender")
    private String gender;

    @Column(name = "pick")
    private String pick;

    @Column(name = "zip")
    private Integer zip;
}