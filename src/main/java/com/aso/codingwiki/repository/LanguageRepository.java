package com.aso.codingwiki.repository;

import com.aso.codingwiki.model.language.LanguageEntity;
import com.aso.codingwiki.model.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LanguageRepository extends JpaRepository<LanguageEntity,Long> {

    List<LanguageEntity> findByLanguage(String Language);
}
