package com.aso.codingwiki.model.language;


import com.aso.codingwiki.model.comment.CommentEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SellLanguageAllResponse {

    private long id;
    private String language;

    public SellLanguageAllResponse(LanguageEntity entity){
        this.language = entity.getLanguage();
        this.id = entity.getId();
    }

}
