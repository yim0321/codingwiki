package com.aso.codingwiki.model.entity.user;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotEmpty;

@Getter
@RequiredArgsConstructor
public class UpdUserRequest {


    private final PasswordEncoder passwordEncoder;

    @NotEmpty
    private long id;
    //@Pattern() 정규식 작성 요망
    @NotEmpty
    private String  oldPw;
    //@Pattern() 정규식 작성 요망
    @NotEmpty
    private String  newPw;


    public void changePw(UserEntity entity){
        if(passwordEncoder.matches(oldPw,entity.getUserPw())){
            entity.setUserPw(passwordEncoder.encode(newPw));
        }
        
    }

}
