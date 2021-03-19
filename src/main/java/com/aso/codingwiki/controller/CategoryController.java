package com.aso.codingwiki.controller;

import com.aso.codingwiki.model.category.CategoryEntity;
import com.aso.codingwiki.model.language.LanguageEntity;
import com.aso.codingwiki.service.CategoryService;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;

    /**
     * create
     */
    @PostMapping("/category")
    public long insCategory(@RequestBody InsCategoryRequest insCategoryRequest){

        return service.insCategory(insCategoryRequest.getCategory(),insCategoryRequest.getLanguageID());
    }

    /**
     * read
     */

    //두개 분리해서 작성하기
//    @GetMapping("/category/{languageID}/{categoryId}")
//    public List<CategoryResponse> sellCategoryLanguage(
//            @PathVariable(name = "languageID") long languageId,
//            @PathVariable(name = "categoryId") long categoryId){
//
//        List<CategoryEntity> entityList;
//
//        if(categoryId == 0){
//            entityList  = service.sellCategoryInLanguage(languageId);
//        }
//        else {
//            entityList = service.sellCategory(categoryId);
//        }
//        return entityList
//                .stream()
//                .map((categoryEntity)->new CategoryResponse(categoryEntity))
//                .collect(Collectors.toList());
//    }
    @GetMapping("/category/languageID/{languageID}")
    public List<CategoryResponse> sellCategoryInLanguage(
            @PathVariable(name = "languageID") long languageId){
        List<CategoryEntity> entityList  = service.sellCategoryInLanguage(languageId);
        return entityList
                .stream()
                .map((categoryEntity)->new CategoryResponse(categoryEntity))
                .collect(Collectors.toList());
    }

    @GetMapping("/category/categoryId/{categoryId}")
    public CategoryResponse sellCategory(
            @PathVariable(name = "categoryId") long categoryId){
        CategoryEntity categoryEntity  = service.sellCategory(categoryId);
        return new CategoryResponse(categoryEntity);
    }


    /**
     * delete
     */

    @DeleteMapping("/category/{categoryId}")
    public long delCategory(
            @PathVariable(name = "categoryId") long categoryId){
        return service.delCategory(categoryId);
    }
    
    
    
    
    

    /**
     * innerclass Request
     */

    @Data
    static class InsCategoryRequest{

        private String category;
        private long languageID;

    }
    /**
     * innerclass Response
     */
    @Data
    static class CategoryResponse{

        private long id;
        private String category;

        @Builder
        public CategoryResponse(CategoryEntity categoryEntity) {
            this.id = categoryEntity.getId();
            this.category = categoryEntity.getCategory();
        }
    }

}
