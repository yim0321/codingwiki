package com.aso.codingwiki.service;

import com.aso.codingwiki.exception.CategoryNotFoundException;
import com.aso.codingwiki.exception.LanguageNotFoundException;
import com.aso.codingwiki.exception.OverlapCategoryException;
import com.aso.codingwiki.model.category.CategoryEntity;
import com.aso.codingwiki.model.language.LanguageEntity;
import com.aso.codingwiki.repository.CategoryRepository;
import com.aso.codingwiki.repository.LanguageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;
    private final LanguageRepository languageRepository;

    public long insCategory(String category,long languageID) {

        Optional<LanguageEntity> languageEntity_ = languageRepository.findById(languageID);
        if(!languageEntity_.isPresent()){
            throw new LanguageNotFoundException("없는 프로그래밍언어 입니다.");
        }

        Optional<CategoryEntity> categoryEntity_ = repository.findByCategory(category);
        if(categoryEntity_.isPresent()){
            throw new OverlapCategoryException("중복된 카테고리입니다.");
        }

        CategoryEntity categoryEntity = CategoryEntity.builder().
                languageEntity(languageEntity_.get()).
                category(category).
                build();
        repository.save(categoryEntity);
        return  categoryEntity.getId();
    }


    public List<CategoryEntity> sellCategoryInLanguage(long languageId) {
        return repository.findByLanguageEntity(languageId);
    }

    public CategoryEntity sellCategory(long categoryId) {
        Optional<CategoryEntity> categoryEntity_ = repository.findById(categoryId);
        if(categoryEntity_.isPresent()) {
            throw new CategoryNotFoundException("없는 카테고리 입니다.");
        }
        return categoryEntity_.get();
    }

    public long delCategory(long categoryId) {
        Optional<CategoryEntity> categoryEntity_ = repository.findById(categoryId);
        if(categoryEntity_.isPresent()) {
            throw new CategoryNotFoundException("없는 카테고리 입니다.");
        }
        repository.delete(categoryEntity_.get());
        return categoryEntity_.get().getId();

    }
}
