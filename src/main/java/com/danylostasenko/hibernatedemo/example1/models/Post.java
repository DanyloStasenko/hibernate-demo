package com.danylostasenko.hibernatedemo.example1.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity(name = "Post")
@Table(name = "post")
@Data
public class Post {

    @Id
    private Long id;

    private String title;
}