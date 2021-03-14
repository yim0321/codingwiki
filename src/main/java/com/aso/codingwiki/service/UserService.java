package com.aso.codingwiki.service;

import com.aso.codingwiki.model.entity.user.InsUserRequest;
import com.aso.codingwiki.model.entity.user.UpdUserRequest;
import com.aso.codingwiki.model.entity.user.UserEntity;
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
        //오류처리로 변경해야함
        if(!userEntity_.isPresent()){
            UserEntity user = new UserEntity(insUserRequest.getUserEmail(),insUserRequest.getUserPw());
            repository.save(user);
            return user.getId();
        }
       return 0;
    }


    public long UpdUser(UpdUserRequest updUserRequest) {

        Optional<UserEntity> userEntity_ = repository.findById(updUserRequest.getId());
        //오류처리로 변경해야함
        if(userEntity_.isPresent()){
            UserEntity userEntity = userEntity_.get();
            if(passwordEncoder.matches(updUserRequest.getOldPw(), userEntity.getUserPw())){
                userEntity.setUserPw(passwordEncoder.encode(updUserRequest.getNewPw()));
            return userEntity.getId();
            }
        }
        return 0;


    }
}
