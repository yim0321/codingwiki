package com.aso.codingwiki.repository;

import com.aso.codingwiki.model.category.CategoryEntity;

import com.aso.codingwiki.model.language.LanguageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity,Long> {

    List<CategoryEntity> findByLanguageEntity(LanguageEntity languageEntity);

    Optional<CategoryEntity> findByCategory(String category);


}
