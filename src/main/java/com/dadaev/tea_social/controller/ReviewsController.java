package com.dadaev.tea_social.controller;

import com.dadaev.tea_social.Service.ReviewService;
import com.dadaev.tea_social.dto.*;

import com.dadaev.tea_social.model.User;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/reviews")
public class ReviewsController {
    private ReviewService reviewService;

    public ReviewsController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<ReviewsPageResponse> getFeedReviews(@RequestParam(name = "offset") int offset, @RequestParam(name = "limit") int pageSize) {
        ReviewsPageResponse feedPage = reviewService.getFeedPage(offset, pageSize);
        return ResponseEntity.ok(feedPage);
    }

    @PostMapping
    public ResponseEntity<ReviewPostResponse> postReview(@Valid @ModelAttribute CreateReviewRequest request, @AuthenticationPrincipal User currentUser) {
        return ResponseEntity.ok(reviewService.createReview(request, currentUser));
    }

    @PostMapping("/{postId}/favorite")
    public ResponseEntity<ReviewPostResponse> toggleFavorite(@PathVariable Long postId, @AuthenticationPrincipal User currentUser) {
        return ResponseEntity.ok(reviewService.toggleFavorite(postId, currentUser));
    }


}
