package com.aso.codingwiki.model.comment;

import com.aso.codingwiki.model.board.BoardEntity;
import com.aso.codingwiki.model.common.DateTime;
import lombok.AccessLevel;
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

    private String comment;

}
