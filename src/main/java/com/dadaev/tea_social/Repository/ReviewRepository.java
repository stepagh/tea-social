package com.dadaev.tea_social.Repository;

import com.dadaev.tea_social.model.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT r FROM Review r LEFT JOIN FETCH r.author LEFT JOIN FETCH r.tea")
    Slice<Review> findReviewPage(Pageable pageable);
    Optional<Review> findById(Long id);

    @Query("""
    SELECT r FROM Review r
    LEFT JOIN FETCH r.author
    LEFT JOIN FETCH r.tea
    WHERE r.author.id = :id
    """)
    Slice<Review> findProfilePosts(Long id, Pageable pageable);


    @Query("""
    SELECT r FROM UserProfile u
    JOIN u.favoriteReviews r
    LEFT JOIN FETCH r.tea
    LEFT JOIN FETCH r.author
    WHERE u.id = :userId
    """)
    Slice<Review> findFavoritePosts(Long userId, Pageable pageable);

    int countDistinctByAuthor_Id(Long id);
}
