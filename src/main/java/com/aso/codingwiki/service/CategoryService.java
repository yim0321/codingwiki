package com.aso.codingwiki.service;

import com.aso.codingwiki.model.category.CategoryEntity;
import com.aso.codingwiki.model.language.LanguageEntity;
import com.aso.codingwiki.repository.CategoryRepository;
import com.aso.codingwiki.repository.LanguageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;
    private final LanguageRepository languageRepository;

    public long insCategory(String category,long languageID) {

        Optional<LanguageEntity> languageEntity_= languageRepository.findById(languageID);
        if(languageEntity_.isPresent()){

            CategoryEntity categoryEntity = new CategoryEntity(category,languageEntity_.get());
            repository.save(categoryEntity);
            return  categoryEntity.getId();
        }
        return 0;
    }


    public List<CategoryEntity> sellCategoryLanguage(long languageId) {
        return repository.findByLanguageEntity(languageId);
    }

    public List<CategoryEntity> sellCategory(long categoryId) {
        ArrayList<CategoryEntity> list = new ArrayList<>();
        Optional<CategoryEntity> categoryEntity_ = repository.findById(categoryId);
        if(categoryEntity_.isPresent()) {
            list.add(categoryEntity_.get());
        }
        return list;
    }

    public long delCategory(long categoryId) {
        Optional<CategoryEntity> categoryEntity_ = repository.findById(categoryId);
        if(categoryEntity_.isPresent()) {
            repository.delete(categoryEntity_.get());
            return categoryEntity_.get().getId();
        }
        return 0;

    }
}
