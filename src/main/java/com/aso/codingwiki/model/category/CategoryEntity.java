package com.aso.codingwiki.model.category;

import com.aso.codingwiki.model.language.LanguageEntity;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@SequenceGenerator(
        name = "category_seq",
        initialValue = 1,
        allocationSize = 1)
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "category_id")
    private long id;

    //    @Column(unique = true,length = 20)//유니크 20자
    private String category;//목차 이름

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id")
    private LanguageEntity languageEntity;

    public CategoryEntity(String category) {
        this.category = category;
    }

}
