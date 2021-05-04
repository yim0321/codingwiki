package com.aso.codingwiki.repository;

import com.aso.codingwiki.model.board.BoardEntity;
import com.aso.codingwiki.model.category.CategoryEntity;
import com.aso.codingwiki.model.language.LanguageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity,Long> {

    List<BoardEntity> findByCategoryEntity(CategoryEntity categoryEntity);

//    @Query("SELECT b FROM  PopularBoardEntity AS p JOIN  BoardEntity AS b where p.languageEntity = :languageEntity")
//    List<BoardEntity> findByLanguageEntity(@Param("languageEntity") LanguageEntity languageEntity);

}
