package com.talkon.talkon.config.security.filter;

import com.auth0.jwt.JWT;
import com.talkon.talkon.utils.JWTUtils;
import com.talkon.talkon.dtos.responce.AppErrorDto;
import com.talkon.talkon.dtos.responce.DataDto;
import com.talkon.talkon.dtos.user.user.LoginDto;
import com.talkon.talkon.dtos.user.user.SessionDto;
import com.talkon.talkon.dtos.user.user.UserDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.talkon.talkon.entities.user.User;
import com.talkon.talkon.exceptions.user.UserBlockedException;
import com.talkon.talkon.exceptions.user.UserNotFoundException;
import com.talkon.talkon.repositories.user.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Date;

@Slf4j
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    private final ObjectMapper mapper;

    private final UserRepository repository;

    public AuthenticationFilter(AuthenticationManager authenticationManager, ObjectMapper mapper, UserRepository repository) {
        this.authenticationManager = authenticationManager;
        this.mapper = mapper;
        this.repository = repository;
        super.setFilterProcessesUrl("/api/login");
    }

    @Override
    @Transactional(dontRollbackOn = {UserBlockedException.class, UserNotFoundException.class})
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            LoginDto loginDto = mapper.readValue(request.getReader(), LoginDto.class);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDto.getPhoneNumber(), loginDto.getCode());
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

        User changeUser = repository.findByPhoneNumberAndDeletedFalse(user.getUsername()).get();
        changeUser.setTryCount(0);

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        if (changeUser.isFirstTime())
            response.setStatus(222);

        else {
            changeUser.setFirstTime(true);
            response.setStatus(223);
        }

        repository.save(changeUser);
        mapper.writeValue(response.getOutputStream(), new DataDto<SessionDto>(sessionDto));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        log.error("Error logging in: {}", failed.getMessage());
        response.setHeader("error", failed.getMessage());
        response.setStatus(HttpStatus.FORBIDDEN.value());
        DataDto<AppErrorDto> responseDTO = new DataDto<>(new AppErrorDto(failed.getLocalizedMessage(), request.getRequestURI(), HttpStatus.FORBIDDEN, failed.getMessage()));
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        mapper.writeValue(response.getOutputStream(), responseDTO);
    }
}
