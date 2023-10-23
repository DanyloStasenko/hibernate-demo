package com.danylostasenko.hibernatedemo.example2WithEntityGraph;

import com.danylostasenko.hibernatedemo.example2WithEntityGraph.models.UserDetails;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDetailsRepositoryWithN1Fixed extends JpaRepository<UserDetails, String> {

//    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, value = "user_details_entity_graph") // 1st approach - Repo settings (2/2)
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = "addresses") // 2nd approach - Only repo settings required (1/1)
    List<UserDetails> findByNameContaining(String text);
}
