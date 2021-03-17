package com.aso.codingwiki.repository;

import com.aso.codingwiki.model.board.BoardEntity;
import com.aso.codingwiki.model.category.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity,Long> {

    List<BoardEntity> findByCategoryEntity(CategoryEntity categoryEntity);
}
