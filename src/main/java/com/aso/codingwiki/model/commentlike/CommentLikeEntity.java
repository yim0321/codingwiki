package com.aso.codingwiki.model.commentlike;

import com.aso.codingwiki.model.comment.CommentEntity;
import com.aso.codingwiki.model.common.DateTime;
import com.aso.codingwiki.model.user.UserEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentLikeEntity extends DateTime {

    @Id
    @GeneratedValue
    @Column(name = "comment_like_id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private CommentEntity commentEntity;

    @OneToOne(fetch = FetchType.LAZY)
    private UserEntity userEntity;

    private int like;

}
