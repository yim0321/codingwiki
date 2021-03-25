package com.aso.codingwiki.repository;

import com.aso.codingwiki.model.board.BoardEntity;
import com.aso.codingwiki.model.board.QBoardEntity;
import com.aso.codingwiki.model.category.CategoryEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class BoardDslRepository {

    @Autowired
    EntityManager entityManager;

    public BoardEntity test(CategoryEntity categoryEntity){

        JPAQueryFactory queryFactory =  new JPAQueryFactory(entityManager);
        QBoardEntity qBoardEntity = new QBoardEntity("A");
        BoardEntity boardEntity = queryFactory
                .select(qBoardEntity)
                .from(qBoardEntity)
                .where(qBoardEntity.categoryEntity.eq(categoryEntity))
                .orderBy(
                        qBoardEntity.avgStarPoint.desc(),
                        qBoardEntity.views.desc())
                .fetchFirst();

        return boardEntity;


    }
}

