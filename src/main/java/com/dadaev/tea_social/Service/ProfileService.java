package com.dadaev.tea_social.Service;

import com.dadaev.tea_social.Repository.BookingRepository;
import com.dadaev.tea_social.Repository.ProfileRepository;
import com.dadaev.tea_social.Repository.ReviewRepository;
import com.dadaev.tea_social.dto.*;
import com.dadaev.tea_social.exceptions.ResourceNotFoundException;
import com.dadaev.tea_social.mapper.BookingMapper;
import com.dadaev.tea_social.mapper.ReviewMapper;
import com.dadaev.tea_social.mapper.ProfileMapper;
import com.dadaev.tea_social.model.Booking;
import com.dadaev.tea_social.model.Review;
import com.dadaev.tea_social.model.User;
import com.dadaev.tea_social.model.UserProfile;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileMapper profileMapper;
    private final ProfileRepository profileRepository;
    private final ReviewMapper reviewMapper;
    private final ReviewRepository reviewRepository;
    private final BookingRepository bookingRepository;
    private final FileStorageService fileStorageService;

    private final BookingMapper bookingMapper;

    public ProfileBundleResponse getProfileBundle(User currentUser) {
        UserProfile userProfile = profileRepository.findById(currentUser.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found"));
        UserProfileResponse userProfileResponse = profileMapper.toResponse(userProfile);
        Long userId = userProfile.getId();

        PageRequest pageRequest = PageRequest.of(0, 10, Sort.Direction.DESC, "r.createdAt");

        Slice<ReviewPostResponse> postSlice = reviewRepository.findProfilePosts(userId, pageRequest).map(reviewMapper::toResponse);
        Slice<ReviewPostResponse> favoriteSlice = reviewRepository.findFavoritePosts(userId, pageRequest).map(reviewMapper::toResponse);
        int teasRated = reviewRepository.countDistinctByAuthor_Id(userId);
        int favoritesCount = profileRepository.countDistinctByFavoriteReviewsId(userId);
        return ProfileBundleResponse.from(userProfileResponse, postSlice, favoriteSlice, teasRated, favoritesCount);
    }

    public void initializeProfile(User user) {
        UserProfile userProfile = new UserProfile(user);
        profileRepository.save(userProfile);
    }

    public ReviewsPageResponse getProfilePosts(int offset, int limit, User currentUser) {
        PageRequest pageRequest = PageRequest.of(offset / limit, limit, Sort.Direction.DESC, "r.createdAt");
        Slice<ReviewPostResponse> reviewsSlice = reviewRepository.findProfilePosts(currentUser.getId(), pageRequest).map(reviewMapper::toResponse);
        return ReviewsPageResponse.from(reviewsSlice);
    }

    public ReviewsPageResponse getFavoriteProfilePosts(int offset, int limit, User currentUser) {
        PageRequest pageRequest = PageRequest.of(offset / limit, limit, Sort.Direction.DESC, "r.createdAt");
        Slice<ReviewPostResponse> reviewsSlice = reviewRepository.findFavoritePosts(currentUser.getId(), pageRequest).map(reviewMapper::toResponse);
        return ReviewsPageResponse.from(reviewsSlice);
    }

    public BookingsPageResult getProfileBookings(int offset, int limit, User user) {
        PageRequest pageRequest = PageRequest.of(offset / limit, limit, Sort.Direction.DESC, "b.date", "b.timeSlot");
        Slice<BookingDto> bookingSlice = bookingRepository.findBookingsByUserProfile_Id(user.getId(), pageRequest).map(bookingMapper::toBookingDto);
        return BookingsPageResult.from(bookingSlice);
    }
    @Transactional
    public void toggleFavoriteReview(Review review, Long userId) {
        UserProfile userProfile = profileRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Profile not found"));
        Set<Review> favoriteReviews = userProfile.getFavoriteReviews();

        if (favoriteReviews.contains(review)) {
            favoriteReviews.remove(review);
        }
        else {
            favoriteReviews.add(review);
        }
    }



    @Transactional
    public UserProfileResponse updateBio(UpdateBioRequest request, User currentUser) {
        UserProfile userProfile = profileRepository.findById(currentUser.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found"));
        userProfile.setBio(request.bio());
        return profileMapper.toResponse(userProfile);
    }

    @Transactional
    public AvatarUploadResponse uploadAvatar(MultipartFile avatarFile, User currentUser) {
        UserProfile userProfile = profileRepository.findById(currentUser.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found"));
        String avatarUrl = fileStorageService.saveImage(avatarFile);
        userProfile.setAvatarUrl(avatarUrl);
        return new AvatarUploadResponse(avatarUrl);
    }
}
