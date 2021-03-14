package com.aso.codingwiki.service;

import com.aso.codingwiki.model.entity.user.InsUserRequest;
import com.aso.codingwiki.model.entity.user.UpdUserRequest;
import com.aso.codingwiki.model.entity.user.UserEntity;
import com.aso.codingwiki.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository repository;


    public long insUser(InsUserRequest insUserRequest) {

        UserEntity user = new UserEntity(insUserRequest.getUserEmail(),insUserRequest.getUserPw());
        repository.save(user);
        return user.getId();
    }


    public long UpdUser(UpdUserRequest updUserRequest) {

        Optional<UserEntity> userEntity_ = repository.findById(updUserRequest.getId());

        if(!userEntity_.isPresent()){
            //오류처리
        }

        updUserRequest.changePw(userEntity_.get());


        return 0;
    }
}
