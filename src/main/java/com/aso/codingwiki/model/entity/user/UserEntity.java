package com.aso.codingwiki.model.entity.user;

import com.aso.codingwiki.model.common.CommonDateTime;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity//jpa entity 등록
@Getter//getter 생성
@NoArgsConstructor(access = AccessLevel.PROTECTED)//기본생성자 생성 (접근지정자= PROTECTED)
public class UserEntity extends CommonDateTime {

    @Id @GeneratedValue//id 프라머리키 설정, GeneratedValue 자동 값증가
    @Column(name = "user_id")
    private long id;

    private String userEmail;//유저 아이디

    private String userPw;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    public UserEntity(String userEmail, String userPw) {
        this.userEmail = userEmail;
        this.userPw = userPw;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }

    private String roles = "ROLE_USER";//USER,ADMIN

    public List<String> getRoleList() {
        if(this.roles.length() > 0) {
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }
    
}
