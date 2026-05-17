package com.dadaev.tea_social.Service;

import com.dadaev.tea_social.Repository.ReviewRepository;
import com.dadaev.tea_social.dto.FeedPageDTO;
import com.dadaev.tea_social.dto.ReviewPostDTO;
import com.dadaev.tea_social.mapper.ReviewMapper;
import com.dadaev.tea_social.model.Review;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    ReviewRepository reviewRepository;
    ReviewMapper reviewMapper;
    public ReviewService(ReviewRepository reviewRepository, ReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
    }

    public FeedPageDTO getFeedPage(int offset, int pageSize) {
        Pageable pageable = PageRequest.of(offset / pageSize, pageSize, Sort.by("createdAt").descending());
        Slice<Review> page = reviewRepository.findReviewPage(pageable);
        List<ReviewPostDTO> posts = page.getContent().stream().map(reviewMapper::toDto).toList();
        int nextOffset = page.hasNext() ? offset + pageSize : -1;
        FeedPageDTO feedPage = new FeedPageDTO(posts, nextOffset, page.hasNext());
        return feedPage;
    }
}
