package com.aso.codingwiki.service;

import com.aso.codingwiki.controller.UserController;
import com.aso.codingwiki.model.user.request.DelUserRequest;
import com.aso.codingwiki.model.user.request.InsUserRequest;
import com.aso.codingwiki.model.user.request.UpdUserRequest;
import com.aso.codingwiki.model.user.UserEntity;
import com.aso.codingwiki.repository.UserRepository;
import lombok.RequiredArgsConstructor;
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


    public long insUser(InsUserRequest insUserRequest) {

        Optional<UserEntity> userEntity_ = repository.findOpByuserEmail(insUserRequest.getUserEmail());
        //비밀번호 암호화
        insUserRequest.passwordEncoder(passwordEncoder);
        /**오류처리로 변경해야함**/
        if(!userEntity_.isPresent()){
            UserEntity user = insUserRequest.builderEntity();
            repository.save(user);
            return user.getId();
        }
       return 0;
    }


    public long updUser(UpdUserRequest updUserRequest) {

        Optional<UserEntity> userEntity_ = repository.findById(updUserRequest.getId());
        /**오류처리로 변경해야함**/
        if(userEntity_.isPresent()){
            UserEntity userEntity = userEntity_.get();
            if(passwordEncoder.matches(updUserRequest.getOldPw(), userEntity.getUserPw())){
                userEntity.setUserPw(passwordEncoder.encode(updUserRequest.getNewPw()));
            return userEntity.getId();
            }
        }
        return 0;


    }

    public long delUser(DelUserRequest delUserRequest) {

        Optional<UserEntity> userEntity_ = repository.findById(delUserRequest.getId());
        /**오류처리로 변경해야함**/
        if(userEntity_.isPresent()){
            UserEntity userEntity = userEntity_.get();
            if(passwordEncoder.matches(delUserRequest.getUserPw(), userEntity.getUserPw())){
                repository.delete(userEntity);
                return userEntity.getId();
            }
        }
        return 0;
    }


}
