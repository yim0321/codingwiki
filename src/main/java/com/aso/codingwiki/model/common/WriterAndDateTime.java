package com.aso.codingwiki.model.common;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.Column;

public class WriterAndDateTime extends DateTime{

    @CreatedBy //생성자 자동주입
    @Column(updatable = false)//수정하지 못하도록 막기
    private String createBy;

    @LastModifiedBy //수정자 자동주입
    private String lastModifiedBy;
}
