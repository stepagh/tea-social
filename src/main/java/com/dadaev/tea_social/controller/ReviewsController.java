package com.dadaev.tea_social.controller;

import com.dadaev.tea_social.Service.ReviewService;
import com.dadaev.tea_social.dto.FeedPageDTO;
import com.dadaev.tea_social.model.Review;

import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("api/reviews")
public class ReviewsController {
    private ReviewService reviewService;

    public ReviewsController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<FeedPageDTO> getFeedReviews(@RequestParam(name = "offset") int offset, @RequestParam(name = "limit") int pageSize) {
        FeedPageDTO feedPage = reviewService.getFeedPage(offset, pageSize);
        return ResponseEntity.ok(feedPage);
    }
}
