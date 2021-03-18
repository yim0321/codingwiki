package com.aso.codingwiki.service;

import com.aso.codingwiki.repository.ImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImgService {

    private final ImgRepository repository;

    public long insImg(){
        repository.save();
    }
}
