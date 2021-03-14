package com.aso.codingwiki.model.entity.user;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdUserRequest {


    @Autowired
    private PasswordEncoder passwordEncoder;



    @NotEmpty
    private long id;
    //@Pattern() 정규식 작성 요망
    @NotEmpty
    private String  oldPw;
    //@Pattern() 정규식 작성 요망
    @NotEmpty
    private String  newPw;


}
