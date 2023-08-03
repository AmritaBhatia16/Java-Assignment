package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity // This tells Hibernate to make a table out of this class
@Table(name = "users")
public class User {
    @Id
    //@GeneratedValue(strategy=GenerationType.AUTO)
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