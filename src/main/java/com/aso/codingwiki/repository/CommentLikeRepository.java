package com.aso.codingwiki.repository;

import com.aso.codingwiki.model.commentlike.CommentLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentLikeRepository extends JpaRepository<CommentLikeEntity,Long> {
}
