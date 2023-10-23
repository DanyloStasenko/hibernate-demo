package com.danylostasenko.hibernatedemo.example2WithEntityGraph;

import com.danylostasenko.hibernatedemo.example2WithEntityGraph.models.UserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDetailsRepositoryWithN1Fixed userRepository;

    private final UserDetailsRepositoryWithN1Problem userDetailsRepositoryN1;

    public UserDetails createUser(){
        UUID uuid = UUID.randomUUID();

        UserDetails userDetails = new UserDetails();

        userDetails.setId(uuid.toString());

        userDetails.setName("name");
        userDetails.setEmail("mail");
        userDetails.setMobileNumber("+123");

        return userRepository.save(userDetails);
    }


    public List<UserDetails> findUserByName(String name) {
        return userRepository.findByNameContaining(name);
    }

    public List<UserDetails> findUserByNameN1(String name) {
        return userDetailsRepositoryN1.findByNameContaining(name);
    }
}
