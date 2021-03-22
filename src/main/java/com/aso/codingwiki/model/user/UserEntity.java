package com.aso.codingwiki.model.user;

import com.aso.codingwiki.model.common.DateTime;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity//jpa entity 등록
@Getter//getter 생성
@NoArgsConstructor(access = AccessLevel.PROTECTED)//기본생성자 생성 (접근지정자= PROTECTED)
@SequenceGenerator(
        name = "user_seq",
        initialValue = 1,
        allocationSize = 1)
public class UserEntity extends DateTime {

    @Id //id 프라머리키 설정,
    @GeneratedValue(strategy = GenerationType.SEQUENCE)// GeneratedValue 자동 값증가
    @Column(name = "user_id")
    private long id;

    private String userEmail;//유저 아이디
    private String userPw;
    private long point;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String roles = "ROLE_USER_BRONZE";//브론즈 실버 골드 플래티넘 다이아 등급으로 구성

    public List<String> getRoleList() {
        if(this.roles.length() > 0) {
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }

    @Builder
    public UserEntity(String userEmail, String userPw) {
        this.userEmail = userEmail;
        this.userPw = userPw;
    }

    public void userPasswordEncoder(PasswordEncoder passwordEncoder){
        this.userPw = passwordEncoder.encode(this.getUserPw());
    }

    public void userPasswordEncoder(PasswordEncoder passwordEncoder,String newPassword){
        this.userPw = passwordEncoder.encode(newPassword);
    }

}
