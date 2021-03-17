package com.aso.codingwiki.model.board;

import com.aso.codingwiki.model.category.CategoryEntity;
import com.aso.codingwiki.model.common.DateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    private float sumStarPoint;//별점
    private long views;//조회수

    @Builder
    public BoardEntity(String boardTitle,String boardContents){
        this.boardTitle = boardTitle;
        this.boardContents = boardContents;
    }



    public void setCategoryEntity(CategoryEntity categoryEntity) {
        this.categoryEntity = categoryEntity;
    }
}
