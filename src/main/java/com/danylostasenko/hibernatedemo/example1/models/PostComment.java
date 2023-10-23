package com.danylostasenko.hibernatedemo.example1.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity(name = "PostComment")
@Table(name = "post_comment")
@Data
public class PostComment {

    @Id
    private Long id;

    @ManyToOne
    private Post post;

    private String review;
}