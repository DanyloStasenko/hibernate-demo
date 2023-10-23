package com.danylostasenko.hibernatedemo.example2WithEntityGraph;

import com.danylostasenko.hibernatedemo.example2WithEntityGraph.models.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDetailsRepositoryWithN1Problem extends JpaRepository<UserDetails, String> {

    List<UserDetails> findByNameContaining(String text);
}
