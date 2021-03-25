package com.aso.codingwiki.model;

import com.aso.codingwiki.model.board.BoardEntity;
import com.aso.codingwiki.model.category.CategoryEntity;
import com.aso.codingwiki.model.language.LanguageEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PopularBoardEntity {

    @Id
    @GeneratedValue
    @Column(name = "main_board_id")
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private BoardEntity boardEntity;

    @ManyToOne
    @JoinColumn(name = "language_id")
    private LanguageEntity languageEntity;


    @Builder
    public PopularBoardEntity(BoardEntity boardEntity,LanguageEntity languageEntity){
        this.boardEntity = boardEntity;
        this.languageEntity = languageEntity;
    }

    public void setBoardEntity(BoardEntity boardEntity,LanguageEntity languageEntity) {
        this.boardEntity = boardEntity;
        this.languageEntity = languageEntity;
    }
}
