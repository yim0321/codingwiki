package com.aso.codingwiki.service;


import com.aso.codingwiki.model.language.LanguageEntity;
import com.aso.codingwiki.model.language.SellLanguageAllReponse;
import com.aso.codingwiki.repository.LanguageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LanguageService {

    private final LanguageRepository repository;

    public List<SellLanguageAllReponse> sellLanguageAll() {
        List<LanguageEntity> languageEntities = repository.findAll();

        return languageEntities
                .stream()
                .map(language-> new SellLanguageAllReponse(language))
                .collect(Collectors.toList());
    }


    public long insLanguage(String language) {
        List<LanguageEntity>languageEntities = repository.findByLanguage(language);
        if(languageEntities.size()<=0) {
            LanguageEntity languageEntity = new LanguageEntity(language);
            repository.save(languageEntity);
            return languageEntity.getId();
        }
        return 0;
    }


    public long delLanguage(Long id) {
        Optional<LanguageEntity> languageEntity_ = repository.findById(id);
        if(languageEntity_.isPresent()){
            repository.delete(languageEntity_.get());
            return id;
        }
        return 0;
    }
}
