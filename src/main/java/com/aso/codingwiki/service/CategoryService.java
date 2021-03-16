package com.aso.codingwiki.service;

import com.aso.codingwiki.model.category.CategoryEntity;
import com.aso.codingwiki.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository respository;

    public long insCategory(String category) {
        CategoryEntity categoryEntity = new CategoryEntity(category);
        respository.save(categoryEntity);
        return  categoryEntity.getId();
    }


    public List<CategoryEntity> sellCategoryLanguage(long languageId) {
        return respository.findByLanguageEntity(languageId);
    }

    public List<CategoryEntity> sellCategory(long categoryId) {
        ArrayList<CategoryEntity> list = new ArrayList<>();
        Optional<CategoryEntity> categoryEntity_ = respository.findById(categoryId);
        if(categoryEntity_.isPresent()) {
            list.add(categoryEntity_.get());
        }
        return list;
    }

    public long delCategory(long categoryId) {
        Optional<CategoryEntity> categoryEntity_ = respository.findById(categoryId);
        if(categoryEntity_.isPresent()) {
            respository.delete(categoryEntity_.get());
            return categoryEntity_.get().getId();
        }
        return 0;

    }
}
