package com.aso.codingwiki.security.filter.jwt;

import com.aso.codingwiki.model.user.UserEntity;
import com.aso.codingwiki.repository.UserRepository;
import com.aso.codingwiki.security.auth.PrincipalDetails;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class JwtAuthorizationFilter extends BasicAuthenticationFilter{


    private UserRepository repository;


    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository repository) {
        super(authenticationManager);
        this.repository = repository;

    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String jwtheader = request.getHeader("Authorization");

        if(jwtheader == null || !jwtheader.startsWith("Bearer")) {
            chain.doFilter(request, response);
            return;
        }

        String jwtToken = request.getHeader("Authorization").replace("Bearer ", "");

        String userEmail =
                JWT.require(Algorithm.HMAC512("codingwiki")).build().verify(jwtToken).getClaim("userEmail").asString();



        if(userEmail != null) {
            UserEntity user = repository.findByuserEmail(userEmail);

            PrincipalDetails principalDetails = new PrincipalDetails(user);

            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);

            SecurityContextHolder.getContext().getAuthentication();

            request.setAttribute("userEmail",userEmail);

            chain.doFilter(request, response);
        }
    }
}
