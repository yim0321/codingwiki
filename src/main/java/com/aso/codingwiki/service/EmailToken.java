package com.aso.codingwiki.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailToken {

    Long expiredTime = 1000 * 60L * 60L * 2L; // 토큰 유효 시간 (2시간)
    String key = "emailKey";//키값 나중에 변경

    public String createToken(String userId){

        Date ext = new Date(); // 토큰 만료 시간
        ext.setTime(ext.getTime() + expiredTime);
        Map<String, Object> payloads = new HashMap<>();
        payloads.put("userId", userId);

        //Header 부분 설정
        Map<String, Object> headers = new HashMap<>();
        headers.put("typ", "JWT");
        headers.put("alg", "HS256");

        String jwt = Jwts.builder()
                .setHeader(headers) // Headers 설정
                .setClaims(payloads) // Claims 설정
                .setExpiration(ext) // 토큰 만료 시간 설정
                .signWith(SignatureAlgorithm.HS256, key.getBytes()) // HS256과 Key로 Sign
                .compact(); // 토큰 생성

        return jwt;
    }
    public String authenticationToken(String token) throws InterruptedException{
        Claims claims = Jwts.parser()
                .setSigningKey(key.getBytes())
                .parseClaimsJws(token)
                .getBody();
        String userId = claims.get("userId",String.class);
        return userId;
    }
}
