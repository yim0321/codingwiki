package com.aso.codingwiki.repository;

import com.aso.codingwiki.model.PopularBoardEntity;
import com.aso.codingwiki.model.category.CategoryEntity;
import com.aso.codingwiki.model.language.LanguageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PopularBoardRepository extends JpaRepository<PopularBoardEntity,Long> {

    Optional<PopularBoardEntity> findByCategoryEntity(CategoryEntity categoryEntity);
    List<PopularBoardEntity> findByLanguageEntity(LanguageEntity languageEntity);



}
