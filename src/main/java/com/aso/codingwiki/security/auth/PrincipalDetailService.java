package com.aso.codingwiki.security.auth;


import com.aso.codingwiki.model.user.UserEntity;
import com.aso.codingwiki.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailService implements UserDetailsService {


    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException{
        UserEntity user = repository.findByUserEmail(userEmail);
        if(user == null){
            throw new UsernameNotFoundException("사용자 아이디를 확인해 주세요.");
        }
        return new PrincipalDetails(user);
    }
}
