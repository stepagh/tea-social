package com.dadaev.tea_social.Service;

import com.dadaev.tea_social.Repository.CommentRepository;
import com.dadaev.tea_social.Repository.ReviewRepository;
import com.dadaev.tea_social.dto.CommentResponse;
import com.dadaev.tea_social.dto.CreateCommentRequest;
import com.dadaev.tea_social.exceptions.ResourceNotFoundException;
import com.dadaev.tea_social.mapper.CommentMapper;
import com.dadaev.tea_social.model.Comment;
import com.dadaev.tea_social.model.Review;
import com.dadaev.tea_social.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final ReviewRepository reviewRepository;


    public CommentResponse createComment(Long postId, CreateCommentRequest request, User currentUser) {
        Review review = reviewRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post doesn't exist"));
        Comment comment = commentMapper.toCommentEntity(request, review, currentUser);
        commentRepository.save(comment);
        return commentMapper.toResponse(comment);
    }
}
