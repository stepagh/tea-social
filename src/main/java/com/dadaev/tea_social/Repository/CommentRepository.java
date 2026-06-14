package com.dadaev.tea_social.Repository;

import com.dadaev.tea_social.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
