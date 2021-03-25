package com.aso.codingwiki.repository;

import com.aso.codingwiki.model.board.BoardEntity;
import com.aso.codingwiki.model.comment.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity,Long> {
    List<CommentEntity> findByBoardEntity(BoardEntity boardEntity);
}
