package com.aso.codingwiki.model.language;


import com.aso.codingwiki.model.common.DateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(
        name = "language_seq",
        initialValue = 1,
        allocationSize = 1)
public class LanguageEntity extends DateTime{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "language_id")
    private long id;

    private String language;//언어 이름

    public LanguageEntity(String language){
        this.language = language;
    }

    public LanguageEntity(Long id) {
        this.id = id;
    }
}
