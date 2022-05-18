package com.talkon.talkon.config.security.filter;

import com.auth0.jwt.JWT;
import com.talkon.talkon.config.security.utils.JWTUtils;
import com.talkon.talkon.dtos.responce.DataDto;
import com.talkon.talkon.dtos.user.LoginDto;
import com.talkon.talkon.dtos.user.SessionDto;
import com.talkon.talkon.dtos.user.UserDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
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

@Slf4j
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    private final ObjectMapper mapper;

    public AuthenticationFilter(AuthenticationManager authenticationManager, ObjectMapper mapper) {
        this.authenticationManager = authenticationManager;
        this.mapper = mapper;
        super.setFilterProcessesUrl("/api/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        LoginDto loginDto = null;
        try {
            loginDto = mapper.readValue(request.getReader(), LoginDto.class);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
            return authenticationManager.authenticate(authenticationToken);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        UserDetails user = (UserDetails) authResult.getPrincipal();

        Date expiryForAccessToken = JWTUtils.getExpiry();

        Date expiryForRefreshToken = JWTUtils.getExpiryForRefreshToken();

        Date issuedAt = new Date();

        String accessToken = JWT.create()
                .withSubject("" + user.getId())
                .withExpiresAt(expiryForAccessToken)
                .withIssuer(request.getRequestURL().toString())
                .withIssuedAt(issuedAt)
                .withClaim("withClaim", user.getAuthorities().toString())
                .sign(JWTUtils.getAlgorithm());

        String refreshToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(expiryForRefreshToken)
                .withIssuer(request.getRequestURL().toString())
                .withExpiresAt(issuedAt)
                .sign(JWTUtils.getAlgorithm());

        SessionDto sessionDto = SessionDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .expiryForAccessToken(expiryForAccessToken.getTime())
                .expiryForRefreshToken(expiryForRefreshToken.getTime())
                .issuedAd(issuedAt.getTime()).build();

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        mapper.writeValue(response.getOutputStream(), new DataDto<SessionDto>(sessionDto));
    }

}
