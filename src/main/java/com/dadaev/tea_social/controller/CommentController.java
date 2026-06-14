package com.dadaev.tea_social.controller;

import com.dadaev.tea_social.Service.CommentService;
import com.dadaev.tea_social.dto.CommentResponse;
import com.dadaev.tea_social.dto.CreateCommentRequest;
import com.dadaev.tea_social.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/reviews/")
public class CommentController {
    CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{postId}/comments")
    public ResponseEntity<CommentResponse> postComment(@PathVariable("postId") long postId, @RequestBody CreateCommentRequest request, @AuthenticationPrincipal User currentUser) {
        CommentResponse response = commentService.createComment(postId, request, currentUser);
        return ResponseEntity.status(201).body(response);
    }
}
