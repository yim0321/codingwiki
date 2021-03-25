package com.aso.codingwiki.model.comment;

import com.aso.codingwiki.model.board.BoardEntity;
import com.aso.codingwiki.model.common.DateTime;
import com.aso.codingwiki.model.user.UserEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentEntity extends DateTime {

    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private BoardEntity boardEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;


    private String comment;

    public CommentEntity(String comment){
        this.comment = comment;
    }

    public void setCommentMap(BoardEntity boardEntity, UserEntity userEntity){
        this.boardEntity = boardEntity;
        this.userEntity = userEntity;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
