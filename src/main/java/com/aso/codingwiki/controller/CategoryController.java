package com.aso.codingwiki.controller;

import com.aso.codingwiki.model.category.CategoryEntity;
import com.aso.codingwiki.service.CategoryService;
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

        return service.insCategory(insCategoryRequest.getCategory());
    }
    /**
     * read
     */
    //두개 분리해서 작성하기
    @GetMapping("/category/{languageID}/{categoryId}")
    public List<CategoryResponse> sellCategoryLanguage(
            @PathVariable(name = "languageID") long languageId,
            @PathVariable(name = "categoryId") long categoryId){

        List<CategoryEntity> entityList;

        if(categoryId == 0){
            entityList  = service.sellCategoryLanguage(languageId);
        }
        else {
            entityList = service.sellCategory(categoryId);
        }
        return entityList
                .stream()
                .map((categoryEntity)->new CategoryResponse(categoryEntity.getId(),categoryEntity.getCategory()))
                .collect(Collectors.toList());
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
    }
    /**
     * innerclass Response
     */
    @Data
    static class CategoryResponse{
        private long id;
        private String category;

        public CategoryResponse(long id, String category) {
            this.id = id;
            this.category = category;
        }
    }

}
