package com.aso.codingwiki.controller;



import com.aso.codingwiki.model.user.UserEntity;
import com.aso.codingwiki.model.user.request.InsUserRequest;
import com.aso.codingwiki.model.user.request.UpdUserRequest;
import com.aso.codingwiki.service.UserService;
import lombok.Builder;
import lombok.Getter;
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
     */
    @GetMapping("/user")
    public userResponse selUser(Principal principal){

        return new userResponse(service.sellUser(principal.getName()));
    }
    @GetMapping("/user/idcheck/{id}")
    public boolean idCheck(@PathVariable(name = "id") String UserId){
        return service.idCheck(UserId);
    }

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
    @Getter
    static class userResponse{
        private String userEmail;
        private String name;
        private String roles;

        @Builder
        public userResponse(UserEntity userEntity){
            this.userEmail = userEntity.getUserEmail();
            this.name = userEntity.getName();
            this.roles = userEntity.getRoles();
        }
    }


}
