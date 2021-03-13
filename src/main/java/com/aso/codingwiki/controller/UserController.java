package com.aso.codingwiki.controller;



import com.aso.codingwiki.model.entity.UserEntity;
import com.aso.codingwiki.repository.UserRepository;
import com.aso.codingwiki.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService service;
    private final UserRepository repository;

    @GetMapping("/user")
    public long insUser(@RequestBody @Valid InsUserRequest insUserRequest){

        UserEntity user = new UserEntity(insUserRequest.getUserEmail(),insUserRequest.getUserPw());
        return service.insUser(user);
    }

    @Data
    static class InsUserRequest{

        @NotEmpty
        //@Pattern() 정규식 작성 요망
        private String userEmail;
        //@Pattern() 정규식 작성 요망
        @NotEmpty
        private String userPw;
    }

}
