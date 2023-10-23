package com.danylostasenko.hibernatedemo.example1.controllers;

import com.danylostasenko.hibernatedemo.example1.models.Post;
import com.danylostasenko.hibernatedemo.example1.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping(value = "/posts")
    public Post createPost(){
        return postService.createPost();
    }

}
