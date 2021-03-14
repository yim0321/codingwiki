package com.aso.codingwiki.controller.user;



import com.aso.codingwiki.model.entity.user.InsUserRequest;
import com.aso.codingwiki.model.entity.user.UpdUserRequest;
import com.aso.codingwiki.model.entity.user.UserEntity;
import com.aso.codingwiki.repository.UserRepository;
import com.aso.codingwiki.service.UserService;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService service;
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;


    @PostMapping("/user")
    public long insUser(@RequestBody @Valid InsUserRequest insUserRequest){

        insUserRequest.passwordEncoder(passwordEncoder);
        return service.insUser(insUserRequest);
    }
    @PutMapping("/user")
    public long updUser(@RequestBody  UpdUserRequest updUserRequest){

        System.out.println("usderID"+updUserRequest.getId());
        System.out.println("usderID"+updUserRequest.getOldPw());
        System.out.println("usderID"+updUserRequest.getNewPw());

        return service.UpdUser(updUserRequest);

    }



}
