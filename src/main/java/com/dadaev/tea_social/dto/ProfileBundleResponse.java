package com.dadaev.tea_social.dto;

import org.springframework.data.domain.Slice;

import java.util.List;

public record ProfileBundleResponse(
        UserProfileResponse user,
        List<ReviewPostResponse> posts,
        List<ReviewPostResponse> favorites,
        Integer postsNextOffset,
        Boolean postsHasMore,
        Integer favNextOffset,
        Boolean favHasMore,
        Integer teasRated,
        Integer favoritesCount
) {
   public static ProfileBundleResponse from(UserProfileResponse user, Slice<ReviewPostResponse> postSlice, Slice<ReviewPostResponse> favoriteSlice,
                                            int teasRated, int favoritesCount) {
       List<ReviewPostResponse> posts = postSlice.getContent();
       boolean posthasMore = postSlice.hasNext();
       Integer postsNextOffset = postSlice.hasNext() ? (postSlice.getNumber() + 1) * postSlice.getSize() : -1;

       List<ReviewPostResponse> favorites = favoriteSlice.getContent();
       boolean favHasMore = favoriteSlice.hasNext();
       Integer favNextOffset = favoriteSlice.hasNext()? (favoriteSlice.getNumber() + 1) * favoriteSlice.getSize() : -1;

       return new ProfileBundleResponse(user, posts, favorites, postsNextOffset, posthasMore, favNextOffset, favHasMore, teasRated, favoritesCount);
   }
}
