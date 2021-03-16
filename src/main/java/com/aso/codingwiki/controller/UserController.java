package com.aso.codingwiki.controller;



import com.aso.codingwiki.model.user.request.DelUserRequest;
import com.aso.codingwiki.model.user.request.InsUserRequest;
import com.aso.codingwiki.model.user.request.UpdUserRequest;
import com.aso.codingwiki.repository.UserRepository;
import com.aso.codingwiki.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService service;
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    /**
     * create
     */

    @PostMapping("/user")
    public long insUser(@RequestBody @Valid InsUserRequest insUserRequest){

        return service.insUser(insUserRequest);
    }

    /**
     * read
     */




    /**
     * update
     */
    @PatchMapping("/user")
    public long updUser(@RequestBody UpdUserRequest updUserRequest){

        return service.updUser(updUserRequest);
    }



    /**
     * delete
     */
    @DeleteMapping("/user")
    public long delUser(@RequestBody @Valid DelUserRequest delUserRequest){

        return service.delUser(delUserRequest);
    }



}
