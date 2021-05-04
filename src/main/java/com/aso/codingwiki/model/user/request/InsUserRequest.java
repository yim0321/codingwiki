package com.aso.codingwiki.model.user.request;

import com.aso.codingwiki.model.user.Gender;
import com.aso.codingwiki.model.user.UserEntity;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotEmpty;

@Getter
public class InsUserRequest {

    private String userEmail;
    private String userPw;
    private String name;
    private String gender;

    public UserEntity changeEntity(){
        Gender gender_;
        if(this.gender.equals("mail")){
           gender_= Gender.male;
        }
        else if(this.gender.equals("femail")){
            gender_ = Gender.female;
        }
        else {
            System.out.println(this.gender);
            throw new RuntimeException("정상적인 성별을 입력해주세요");
        }

        return UserEntity.
                builder()
                .userEmail(this.userEmail)
                .userPw(this.userPw)
                .name(this.name)
                .gender(gender_)
                .build();
    }

}
