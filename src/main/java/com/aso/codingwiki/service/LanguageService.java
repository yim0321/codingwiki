package com.aso.codingwiki.service;


import com.aso.codingwiki.exception.LanguageNotFoundException;
import com.aso.codingwiki.exception.OverlapLanguageException;
import com.aso.codingwiki.model.language.LanguageEntity;
import com.aso.codingwiki.model.language.SellLanguageAllResponse;
import com.aso.codingwiki.repository.LanguageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class LanguageService {

    private final LanguageRepository repository;


    public List<LanguageEntity> insLanguage(String language) {

        Optional<LanguageEntity> languageEntity_ = repository.findByLanguage(language);
        if(languageEntity_.isPresent()){
            throw new OverlapLanguageException("이미 같은 프로그램언어가 있습니다.");
        }
        LanguageEntity languageEntity = LanguageEntity.
                builder().
                language(language).
                build();
        repository.save(languageEntity);

        return sellLanguageAll();

    }

    public List<LanguageEntity> sellLanguageAll() {

        List<LanguageEntity> languageEntities = repository.findAll();
        return languageEntities;
    }

    public long delLanguage(Long id) {

        Optional<LanguageEntity> languageEntity_ = repository.findById(id);
        if(languageEntity_.isPresent()){
            throw new LanguageNotFoundException("삭제하고자 하는 프로그래밍언어를 찾을 수 없습니다.");
        }
        repository.delete(languageEntity_.get());
        return id;
    }
}
