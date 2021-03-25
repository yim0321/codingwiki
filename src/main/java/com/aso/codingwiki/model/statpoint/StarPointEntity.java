package com.aso.codingwiki.model.statpoint;

import com.aso.codingwiki.model.board.BoardEntity;
import com.aso.codingwiki.model.common.DateTime;
import com.aso.codingwiki.model.user.UserEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StarPointEntity extends DateTime {

    @Id
    @GeneratedValue
    @Column(name = "star_point_id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private BoardEntity boardEntity;

    @OneToOne(fetch = FetchType.LAZY)
    private UserEntity userEntity;

    private float starPoint;

    @Builder
    public StarPointEntity(BoardEntity boardEntity,UserEntity userEntity,float starPoint){
        this.boardEntity = boardEntity;
        this.userEntity = userEntity;
        this.starPoint = starPoint;
    }

    public void setStarPoint(float starPoint) {
        this.starPoint = starPoint;
    }
}
