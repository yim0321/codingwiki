package com.aso.codingwiki.model.board;

import com.aso.codingwiki.model.category.CategoryEntity;
import com.aso.codingwiki.model.comment.CommentEntity;
import com.aso.codingwiki.model.common.DateTime;
import com.aso.codingwiki.model.user.UserEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardEntity extends DateTime {

    @Id
    @GeneratedValue
    @Column(name = "board_id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoryEntity categoryEntity;//목차

    private String boardTitle;//글제몪
    private String boardContents;//글내용
    private float avgStarPoint;//별점
    private long views;//조회수
    private String uuid;//이미지 uuid

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @Builder
    public BoardEntity(String boardTitle,String boardContents,String uuid){
        this.boardTitle = boardTitle;
        this.boardContents = boardContents;
        this.uuid = uuid;
    }



    public void setCategoryEntity(CategoryEntity categoryEntity) {
        this.categoryEntity = categoryEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public void changeEntity(BoardEntity newBoardEntity){
        this.uuid = newBoardEntity.getUuid();
        this.boardTitle = newBoardEntity.getBoardTitle();
        this.boardContents = newBoardEntity.getBoardContents();
    }

    public void addViews(){
        this.views = this.views+1;
    }


    public void setAvgStarPoint(float avgStarPoint) {
        this.avgStarPoint = avgStarPoint;
    }
}
