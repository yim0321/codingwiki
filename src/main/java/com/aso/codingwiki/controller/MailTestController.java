package com.aso.codingwiki.controller;

import com.aso.codingwiki.service.MailTestService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MailTestController {
    private final MailTestService mailTestService;

    @PostMapping("/mail")
    public void sendEmail(@RequestBody User user){
        mailTestService.emailSend(user.userId);
    }
    @PostMapping("/mailA")
    public void authenticationEmail(@RequestBody Token token) throws InterruptedException {
        mailTestService.authenticationEmail(token.token);
    }
    @PostMapping("/mailB")
    public String findPassword(@RequestBody Token token) throws InterruptedException {
        return mailTestService.findPassword(token.token);
    }

    @Getter
    static class User{
        String userId;
    }
    @Getter
    static class Token{
        String token;
    }
}
