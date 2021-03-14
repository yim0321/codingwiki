package com.aso.codingwiki.model.entity.user;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotEmpty;

@Getter
@RequiredArgsConstructor
public class InsUserRequest {

    @NotEmpty
    //@Pattern() 정규식 작성 요망
    private String userEmail;
    //@Pattern() 정규식 작성 요망
    @NotEmpty
    private String userPw;

    public void passwordEncoder(PasswordEncoder passwordEncoder){
        this.userPw = passwordEncoder.encode(this.getUserPw());
    }
}
