package com.danylostasenko.hibernatedemo.example2WithEntityGraph;

import com.danylostasenko.hibernatedemo.example2WithEntityGraph.models.UserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/users")
    public UserDetails createUser(){
        return userService.createUser();
    }

    @GetMapping(value = "/np1/users/{name}")
    public List<UserDetails> getUserByNameWithN1Problem(@PathVariable String name){
        List<UserDetails> userByName = userService.findUserByNameN1(name);
        log.info("userByName.size(): " + userByName.size());
        return userByName;
    }

    @GetMapping(value = "/np1fixed/users/{name}")
    public List<UserDetails> getUserByNameN1Fixed(@PathVariable String name){
        List<UserDetails> userByName = userService.findUserByName(name);
        log.info("userByName.size(): " + userByName.size());
        return userByName;
    }
}
