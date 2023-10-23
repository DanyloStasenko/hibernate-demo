package com.danylostasenko.hibernatedemo.example1.repositories;

import com.danylostasenko.hibernatedemo.example1.models.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PostCommentRepository extends JpaRepository<PostComment, Long> {

    // N + 1
    @Transactional(readOnly = true)
    @Query(value = """
            SELECT *
            FROM post_comment
            ORDER BY id
            """,
            nativeQuery = true)
    List<PostComment> findAllPostComments_Np1();

    @Transactional(readOnly = true)
    @Query(value = """
            SELECT pc
            FROM PostComment as pc
            JOIN FETCH pc.post
            """)
    List<PostComment> findAllPostComments_fixedNp1();
}
