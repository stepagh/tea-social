package com.dadaev.tea_social.Service;

import com.dadaev.tea_social.Repository.ReviewRepository;
import com.dadaev.tea_social.dto.*;
import com.dadaev.tea_social.exceptions.ResourceNotFoundException;
import com.dadaev.tea_social.mapper.ReviewMapper;
import com.dadaev.tea_social.model.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;
    private final FileStorageService fileStorageService;
    private final TeaService teaService;
    private final ProfileService profileService;

    public ReviewsPageResponse getFeedPage(int offset, int pageSize) {
        Pageable pageable = PageRequest.of(offset / pageSize, pageSize, Sort.by("createdAt").descending());
        Slice<ReviewPostResponse> slice = reviewRepository.findReviewPage(pageable).map(reviewMapper::toResponse);

        return ReviewsPageResponse.from(slice);
    }

    public ReviewPostResponse createReview(CreateReviewRequest request, User currentUser) {
        Tea tea = teaService.getTeaFromRequest(request);

        Review review = reviewMapper.toEntity(request, tea, currentUser);
        ReviewTag reviewTags = reviewMapper.toReviewTagEntity(request);

        review.setTags(reviewTags);
        review.setImageUrl(fileStorageService.saveImage(request.image()));
        review.setComments(new ArrayList<>());
        review = reviewRepository.save(review);

        return reviewMapper.toResponse(review);
    }
    public Slice<ReviewPostResponse> getProfilePosts(Long userId, Pageable pageable){
        return reviewRepository.findProfilePosts(userId, pageable).map(reviewMapper::toResponse);
    }

    public Slice<ReviewPostResponse> getFavoriteProfilePosts(Long userId, Pageable pageable) {
        return reviewRepository.findFavoritePosts(userId, pageable).map(reviewMapper::toResponse);
    }

    @Transactional
    public ReviewPostResponse toggleFavorite(Long postId, User user) {
        Review review = reviewRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post not found/doesn't exist"));
        profileService.toggleFavoriteReview(review, user.getId());
        return reviewMapper.toResponse(review);
    }
}
