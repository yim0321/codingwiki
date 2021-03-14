package com.aso.codingwiki.security.filter.jwt;


import com.aso.codingwiki.model.security.JwtAuthenticateModel;
import com.aso.codingwiki.security.auth.PrincipalDetails;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        // request에 있는 username과 password를 파싱해서 자바 Object로 받기

        try {
            ObjectMapper om = new ObjectMapper();
            JwtAuthenticateModel user = om.readValue(request.getInputStream(), JwtAuthenticateModel.class);
            // 유저네임패스워드 토큰 생성
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(user.getUserEmail(), user.getUserPw());

            Authentication authentication = authenticationManager.authenticate(authenticationToken);//로그인 인증 처리

            return authentication;
        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException {

        PrincipalDetails principalDetailis = (PrincipalDetails) authResult.getPrincipal();


        String jwtToken = JWT.create()
                .withSubject("jwtToken")
                .withExpiresAt(new Date(System.currentTimeMillis()+600000))
                .withClaim("user_id", principalDetailis.getUser().getUserEmail())
                .sign(Algorithm.HMAC512("codingwiki"));
        //response.addHeader("Authorization", "Bearer "+jwtToken);
        response.getWriter().append("Authorization:" ).append("Bearer "+jwtToken);
    }
}