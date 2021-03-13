package com.aso.codingwiki.service;

import com.aso.codingwiki.controller.UserController;
import com.aso.codingwiki.model.entity.UserEntity;
import com.aso.codingwiki.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.Persistence;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository repository;

    public long insUser(UserEntity user) {

        repository.save(user);

        System.out.println(user.getId());
        return 0;
    }



}
