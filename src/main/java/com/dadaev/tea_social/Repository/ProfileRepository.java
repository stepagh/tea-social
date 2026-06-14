package com.dadaev.tea_social.Repository;

import com.dadaev.tea_social.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<UserProfile, Long> {
    int countDistinctByFavoriteReviewsId(Long profileId);
}
