package com.aso.codingwiki.model.board;

import com.aso.codingwiki.model.comment.CommentEntity;
import lombok.Builder;

import java.util.List;

public class BoardResponse {

    private BoardEntity boardEntity;
    private List<CommentEntity> commentEntityList;

    @Builder
    public BoardResponse(BoardEntity boardEntity, List<CommentEntity> commentEntityList){
        this.boardEntity = boardEntity;
        this.commentEntityList = commentEntityList;
    }
}
