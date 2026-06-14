package com.dadaev.tea_social.controller;

import com.dadaev.tea_social.Service.ProfileService;
import com.dadaev.tea_social.dto.*;
import com.dadaev.tea_social.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping
    public ResponseEntity<ProfileBundleResponse> getProfile(@AuthenticationPrincipal User currentUser) {
        return ResponseEntity.ok(profileService.getProfileBundle(currentUser));
    }

    @GetMapping("/posts")
    public ResponseEntity<ReviewsPageResponse> getProfilePosts(@RequestParam int offset, @RequestParam int limit, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(profileService.getProfilePosts(offset, limit, user));
    }

    @GetMapping("/favorites")
    public ResponseEntity<ReviewsPageResponse> getFavoritePosts(@RequestParam int offset, @RequestParam int limit, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(profileService.getFavoriteProfilePosts(offset, limit, user));
    }

    @PatchMapping
    public ResponseEntity<UserProfileResponse> updateBio(@RequestBody UpdateBioRequest request, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(profileService.updateBio(request, user));
    }

    @PostMapping("/avatar")
    public ResponseEntity<AvatarUploadResponse> uploadAvatar(@RequestParam("avatar") MultipartFile avatarFile, @AuthenticationPrincipal User currentUser) {
        return ResponseEntity.ok(profileService.uploadAvatar(avatarFile, currentUser));
    }

}
