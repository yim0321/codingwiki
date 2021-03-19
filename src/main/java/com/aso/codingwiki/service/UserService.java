package com.aso.codingwiki.service;

import com.aso.codingwiki.exception.OverlapUserException;
import com.aso.codingwiki.exception.WrongPasswordException;
import com.aso.codingwiki.model.user.request.UpdUserRequest;
import com.aso.codingwiki.model.user.UserEntity;
import com.aso.codingwiki.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;


    public long insUser(UserEntity userEntity) {

        Optional<UserEntity> userEntity_ = repository.
                findOpByuserEmail(userEntity.getUserEmail());
        if(userEntity_.isPresent()){
            throw new OverlapUserException("중복된 사용자 입니다.");
        }
        //비밀번호 암호화
        userEntity.userPasswordEncoder(passwordEncoder);
        repository.save(userEntity);
       return userEntity.getId();
    }


    public long updUser(UpdUserRequest updUserRequest) {

        Optional<UserEntity> userEntity_ = repository.findById(updUserRequest.getId());
        if(!userEntity_.isPresent()){
            throw new UsernameNotFoundException("사용자를 찾을수 없습니다.");
        }
        UserEntity userEntity = userEntity_.get();
        if(!passwordEncoder.matches(updUserRequest.getOldPw(), userEntity.getUserPw())) {
            throw new WrongPasswordException("비밀번호가 틀렸습니다.");
        }
        userEntity.userPasswordEncoder(passwordEncoder,updUserRequest.getNewPw());
        return userEntity.getId();


    }

    public long delUser(long userId) {

        Optional<UserEntity> userEntity_ = repository.findById(userId);
        /**오류처리로 변경해야함**/
        if(!userEntity_.isPresent()){
            throw new UsernameNotFoundException("사용자를 찾을수 없습니다.");
        }
        UserEntity userEntity = userEntity_.get();
        repository.delete(userEntity);
        return userEntity.getId();
    }


}
