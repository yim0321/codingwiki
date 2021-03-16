package com.aso.codingwiki.model.language;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SellLanguageAllReponse {

    private long id;
    private String language;

    public SellLanguageAllReponse(LanguageEntity entity){
        this.language = entity.getLanguage();
        this.id = entity.getId();
    }

}
