package com.aso.codingwiki.model.user.request;

import com.aso.codingwiki.model.user.UserEntity;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotEmpty;

@Getter
public class InsUserRequest {

    @NotEmpty
    //@Pattern() 정규식 작성 요망
    private String userEmail;
    //@Pattern() 정규식 작성 요망
    @NotEmpty
    private String userPw;

    public UserEntity changeEntity(){
        return UserEntity.
                builder().
                userEmail(this.userEmail).
                userPw(this.userPw)
                .build();
    }

}
