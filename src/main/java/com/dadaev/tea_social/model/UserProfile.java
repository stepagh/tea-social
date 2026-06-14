package com.dadaev.tea_social.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user_profiles")
@Getter
@Setter
@NoArgsConstructor
public class UserProfile {
    @Id
    Long id;
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @MapsId
    User user;
    String bio = "";
    String avatarUrl = "";
//    Integer teasDrunk = 0;
//    Integer teasRated = 0;
//    Integer favoritesCount = 0;

    @ManyToMany
    @JoinTable(name = "user_favorite_reviews",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "review_id"))
    private Set<Review> favoriteReviews = new HashSet<>();


    public UserProfile(User user) {
        this.user = user;
    }
}
