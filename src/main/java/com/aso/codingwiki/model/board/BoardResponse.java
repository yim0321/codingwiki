package com.aso.codingwiki.model.board;

import com.aso.codingwiki.model.comment.CommentEntity;
import com.aso.codingwiki.model.comment.CommentResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class BoardResponse {

    private List<CommentResponse> commentResponse;
    private String boardTitle;
    private String boardContents;
    private float avgStarPoint;
    private long views;

    @Builder
    public BoardResponse( List<CommentEntity> commentEntityList, BoardEntity boardEntity){
        this.commentResponse = commentEntityList
                .stream()
                .map(commentEntity -> new CommentResponse(commentEntity))
                .collect(Collectors.toList());
        this.boardTitle = boardEntity.getBoardTitle();
        this.boardContents = boardEntity.getBoardContents();
        this.avgStarPoint = boardEntity.getAvgStarPoint();
        this.views = boardEntity.getViews();
    }

}

