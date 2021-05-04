package com.aso.codingwiki.service;

import com.aso.codingwiki.model.user.UserEntity;
import com.aso.codingwiki.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@RequiredArgsConstructor
@Service
public class MailTestService {


    private final MailSendService mailSendService;
    private final EmailToken emailToken;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void emailSend(String userId){

        String token = emailToken.createToken(userId);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(userId);
        mailMessage.setSubject("회원가입 이메일 인증");
        mailMessage.setText("인증번호 :"+token);
        mailSendService.sendEmail(mailMessage);

    }
    public void authenticationEmail(String token) throws InterruptedException {

        String userId = emailToken.authenticationToken(token);
        Optional<UserEntity> userEntity_ =
                userRepository.findOpByUserEmail(userId);
        if(!userEntity_.isPresent()){
            throw new UsernameNotFoundException("업는 아이디 입니다.");
        }
        UserEntity userEntity = userEntity_.get();
        userEntity.setRoles("user");
        userRepository.save(userEntity);

    }

    public String findPassword(String token) throws InterruptedException{
        String userId = emailToken.authenticationToken(token);

        Optional<UserEntity> userEntity_ =
                userRepository.findOpByUserEmail(userId);
        if(!userEntity_.isPresent()){
            throw new UsernameNotFoundException("업는 아이디 입니다.");
        }
        UserEntity userEntity = userEntity_.get();
        String newPassword = getRamdomPassword();
        userEntity.setUserPw(newPassword);
        userEntity.userPasswordEncoder(passwordEncoder);
        userRepository.save(userEntity);
        return newPassword;
    }

    public String getRamdomPassword() {
        int len = 12;
        char[] charSet = new char[] {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
        };
        int idx = 0; StringBuffer sb = new StringBuffer();
        for (int i = 0; i < len; i++) {
            idx = (int) (charSet.length * Math.random()); // 36 * 생성된 난수를 Int로 추출 (소숫점제거)
            sb.append(charSet[idx]);
        }
        return sb.toString();
    }

}
