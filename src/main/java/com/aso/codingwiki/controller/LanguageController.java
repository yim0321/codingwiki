package com.aso.codingwiki.controller;


import com.aso.codingwiki.model.language.LanguageEntity;
import com.aso.codingwiki.model.language.SellLanguageAllReponse;
import com.aso.codingwiki.service.LanguageService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class LanguageController {

    private final LanguageService service;

    /**
     * create
     */

    @PostMapping("language")
    public long insLanguage(@RequestBody InsLanguageRequest insLanguageRequest){
        return service.insLanguage(insLanguageRequest.getLanguage());
    }

    /**
     * read
     */

    @GetMapping("language")
    public List<SellLanguageAllReponse> selLanguageAll(){
        List<SellLanguageAllReponse> languageEntity = service.sellLanguageAll();
        return languageEntity;
    }


    /**
     * update
     */
    //보류 사용안할듯
    @PutMapping("language")
    public void updLanguage(){
        
    }


    /**
     * delete
     */
    @DeleteMapping("language/{id}")
    public long delLanguage(@PathVariable(name = "id") long id ){

        return service.delLanguage(id);
    }


    /**
     * innerclass
     */
    @Data
    static class InsLanguageRequest{
        private String language;
    }







}
