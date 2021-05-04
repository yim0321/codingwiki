package com.aso.codingwiki.controller;


import com.aso.codingwiki.model.language.LanguageEntity;
import com.aso.codingwiki.model.language.SellLanguageAllResponse;
import com.aso.codingwiki.service.LanguageService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
public class LanguageController {

    private final LanguageService service;

    /**
     * create
     */
    @PostMapping("language")
    public List<SellLanguageAllResponse> insLanguage(@RequestBody InsLanguageRequest insLanguageRequest){

        List<LanguageEntity> languageEntities = service.insLanguage(insLanguageRequest.getLanguage());
        return languageEntities
                .stream()
                .map(language-> new SellLanguageAllResponse(language))
                .collect(Collectors.toList());
    }

    /**
     * read
     */

    @GetMapping("language")
    public List<SellLanguageAllResponse> selLanguageAll(){
        List<LanguageEntity> languageEntities = service.sellLanguageAll();

        return languageEntities
                .stream()
                .map(language-> new SellLanguageAllResponse(language))
                .collect(Collectors.toList());

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
    @DeleteMapping("language/{languageId}")
    public long delLanguage(@PathVariable(name = "languageId") long languageId ){

        return service.delLanguage(languageId);
    }


    /**
     * innerclass
     */
    @Data
    static class InsLanguageRequest{
        private String language;
    }







}
