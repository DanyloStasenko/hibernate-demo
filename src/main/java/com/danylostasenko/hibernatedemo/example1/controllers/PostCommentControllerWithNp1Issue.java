package com.danylostasenko.hibernatedemo.example1.controllers;

import com.danylostasenko.hibernatedemo.example1.models.PostComment;
import com.danylostasenko.hibernatedemo.example1.services.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/np1/postComments")
public class PostCommentControllerWithNp1Issue {

    private final PostService postService;

    @GetMapping
    public List<PostComment> getPostComments_Np1(){
        log.info("Plain GET with N+1");
        return postService.getPostComments_Np1();
    }

    @GetMapping(value = "/typedQuery")
    public List<PostComment> getPostCommentsUsingTypedQuery_Np1(){
        log.info("Typed Query GET with N+1");
        return postService.getPostCommentsUsingTypedQuery_Np1();
    }

    @GetMapping(value = "/nativeQuery")
    public List<PostComment> getPostCommentsUsingNativeQuery_Np1(){
        log.info("Native Query GET with N+1");
        return postService.getPostCommentsUsingNativeQuery_Np1();
    }
}
