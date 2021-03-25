package com.aso.codingwiki.repository;

import com.aso.codingwiki.model.board.BoardEntity;
import com.aso.codingwiki.model.statpoint.StarPointEntity;
import com.aso.codingwiki.model.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StarPointRepository extends JpaRepository<StarPointEntity,Long> {

    Optional<StarPointEntity> findByBoardEntityAndUserEntity(BoardEntity boardEntity, UserEntity userEntity);

    @Query("select avg(s.starPoint) from StarPointEntity as s where s.boardEntity = :boardEntity")
    float starPointAvg(@Param("boardEntity") BoardEntity boardEntity);



}
