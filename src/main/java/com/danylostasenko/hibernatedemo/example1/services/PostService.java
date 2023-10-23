package com.danylostasenko.hibernatedemo.example1.services;

import com.danylostasenko.hibernatedemo.example1.models.Post;
import com.danylostasenko.hibernatedemo.example1.models.PostComment;
import com.danylostasenko.hibernatedemo.example1.repositories.PostCommentRepository;
import com.danylostasenko.hibernatedemo.example1.repositories.PostRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class PostService {

    @PersistenceContext
    private EntityManager entityManager;

    private final PostRepository postRepository;

    private final PostCommentRepository postCommentRepository;

    @PostConstruct
    public void performSmokeTests(){
        log.info("Calling post construct methods:");
        log.info("1. Calling method: testN1: (N + 1 Queries expected)");
        List<PostComment> postComments = getPostComments_Np1();
        assert postComments.size() > 0;
        log.info("2. Calling method: fixN1: (1 Query expected)");
        postComments = getPostComments_fixedNp1();
        assert postComments.size() > 0;

        log.info("3. Calling method: testTypedQueryN1: (N + 1 Queries expected)");
        postComments = getPostCommentsUsingTypedQuery_Np1();
        assert postComments.size() > 0;
        log.info("4. Calling method: fixTypedQueryN1: (1 Query expected)");
        postComments = getPostCommentsUsingTypedQuery_fixedNp1();
        assert postComments.size() > 0;

        log.info("5. Testing @Repository @Query - Calling method: findAllPostComments: (N + 1 Queries expected)");
        postComments = postCommentRepository.findAllPostComments_Np1();
        assert postComments.size() > 0;
        log.info("6. Testing @Repository @Query - Calling method: findAllPostCommentsFixedN1: (1 Query expected)");
        postComments = postCommentRepository.findAllPostComments_fixedNp1();
        assert postComments.size() > 0;
    }


    @Transactional(readOnly = true)
    public Post createPost() {
        log.info("Creating post");

        Post post = new Post();
        post.setId(1L);
        post.setTitle("First Post");

        return postRepository.save(post);
    }

    @Transactional(readOnly = true)
    public List<PostComment> getPostComments_Np1() {
        List<PostComment> postComments = new ArrayList<>();

        List<Tuple> comments = entityManager.createNativeQuery("""
                        SELECT
                            pc.id AS id,
                            pc.review AS review,
                            pc.post_id AS postId
                        FROM post_comment pc
                        """, Tuple.class)
                .getResultList();

        for (Tuple comment : comments) {
            PostComment postComment = new PostComment();
            String review = (String) comment.get("review");
            Long postId = ((Number) comment.get("postId")).longValue();
            Long postCommentId = ((Number) comment.get("id")).longValue();

            String postTitle = (String) entityManager.createNativeQuery("""
                            SELECT
                                p.title
                            FROM post p
                            WHERE p.id = :postId
                            """)
                    .setParameter("postId", postId)
                    .getSingleResult();

            log.info("The Post " + postTitle + " got this review " + review);
            Post post = new Post();
            post.setId(postId);
            post.setTitle(postTitle);
            postComment.setPost(post);
            postComment.setId(postCommentId);
            postComment.setReview(review);

            postComments.add(postComment);
        }

        return postComments;
    }

    @Transactional(readOnly = true)
    public List<PostComment> getPostComments_fixedNp1() {
        List<PostComment> postComments = new ArrayList<>();

        List<Tuple> comments = entityManager.createNativeQuery("""
                        SELECT
                            pc.id AS id,
                            pc.review AS review,
                            p.title AS postTitle,
                            pc.post_id AS postId
                        FROM post_comment pc
                        JOIN post p ON pc.post_id = p.id
                        """, Tuple.class)
                .getResultList();

        for (Tuple comment : comments) {
            String review = (String) comment.get("review");
            String postTitle = (String) comment.get("postTitle");
            Long postId = ((Number) comment.get("postId")).longValue();
            Long postCommentId = ((Number) comment.get("id")).longValue();

            Post post = new Post();
            PostComment postComment = new PostComment();

            post.setTitle(postTitle);
            post.setId(postId);
            postComment.setReview(review);
            postComment.setPost(post);
            postComment.setId(postCommentId);

            postComments.add(postComment);
            log.info("The Post " + postTitle + " got this review " + review);
        }
        return postComments;
    }

    @Transactional(readOnly = true)
    public List<PostComment> getPostCommentsUsingTypedQuery_Np1() {
        List<PostComment> comments = entityManager
                .createQuery("""
                        SELECT pc
                        FROM PostComment pc
                        """, PostComment.class)
                .getResultList();
        log.debug("Found: " + comments.size() + " comments: " + comments);
        return comments;
    }

    @Transactional(readOnly = true)
    public List<PostComment> getPostCommentsUsingTypedQuery_fixedNp1() {
        List<PostComment> comments = entityManager.createQuery("""
                        SELECT pc
                        FROM PostComment pc
                        JOIN FETCH pc.post p
                        """, PostComment.class)
                .getResultList();

        log.debug("Found: " + comments.size() + " comments: " + comments);
        return comments;
    }

    @Transactional(readOnly = true)
    public List<PostComment> getPostCommentsUsingNativeQuery_Np1() {
        return postCommentRepository.findAllPostComments_Np1();
    }

    @Transactional(readOnly = true)
    public List<PostComment> getPostCommentsUsingNativeQuery_fixedNp1() {
        return postCommentRepository.findAllPostComments_fixedNp1();
    }
}
