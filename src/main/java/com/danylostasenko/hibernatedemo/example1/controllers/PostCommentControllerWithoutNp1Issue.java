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
@RequestMapping("/fixedNp1/postComments")
public class PostCommentControllerWithoutNp1Issue {

    private final PostService postService;

    @GetMapping
    public List<PostComment> fixN1(){
        log.info("Plain GET fixed");
        return postService.getPostComments_fixedNp1();
    }

    @GetMapping(value = "/typedQuery")
    public List<PostComment> fixTypedQueryN1(){
        log.info("Typed Query GET fixed");
        return postService.getPostCommentsUsingTypedQuery_fixedNp1();
    }

    @GetMapping(value = "/nativeQuery")
    public List<PostComment> nativeQueryN1Fixed(){
        log.info("Native Query GET fixed");
        return postService.getPostCommentsUsingNativeQuery_fixedNp1();
    }
}
