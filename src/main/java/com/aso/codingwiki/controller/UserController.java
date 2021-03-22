package com.aso.codingwiki.controller;



import com.aso.codingwiki.model.user.request.InsUserRequest;
import com.aso.codingwiki.model.user.request.UpdUserRequest;
import com.aso.codingwiki.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    /**
     * create
     */

    @PostMapping("/user")
    public long insUser(@RequestBody InsUserRequest insUserRequest){
        return service.insUser(insUserRequest.changeEntity());
    }

    /**
     * read
     * 관리자 페이지에서 사용할듯 일단 패스
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
    public long delUser(Principal principal){
        return service.delUser(principal.getName());
    }

    /**
     * inner class
     */



}
