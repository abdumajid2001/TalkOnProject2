package com.talkon.talkon.config.security.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.talkon.talkon.entities.user.User;
import com.talkon.talkon.exceptions.user.UserNotFoundException;
import com.talkon.talkon.repositories.user.UserRepository;
import com.talkon.talkon.config.security.utils.JWTUtils;
import com.talkon.talkon.dtos.responce.AppErrorDto;
import com.talkon.talkon.dtos.responce.DataDto;
import com.talkon.talkon.dtos.user.UserDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthorizationFilter extends OncePerRequestFilter {

    private final ObjectMapper mapper;
    private final UserRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().equals("/api/login") || request.getServletPath().equals("/access/token") || request.getServletPath().equals("/refresh/token")) {
            filterChain.doFilter(request, response);
            return;
        }
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String token = authorizationHeader.substring("Bearer ".length());

                DecodedJWT decodedJWT = JWTUtils.getVerifier().verify(token);

                String id = decodedJWT.getSubject();
                User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException("User Not found"));
                UserDetails userDetails = new UserDetails(user);

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            } catch (Exception exception) {
                log.error("Error logging in: {}", exception.getMessage());
                response.setHeader("error", exception.getMessage());
                response.setStatus(HttpStatus.FORBIDDEN.value());
                DataDto<AppErrorDto> responseDTO = new DataDto<>(new AppErrorDto(exception.getLocalizedMessage(), request.getRequestURI(), HttpStatus.FORBIDDEN, exception.getMessage()));
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                mapper.writeValue(response.getOutputStream(), responseDTO);
            }

        }
        filterChain.doFilter(request, response);

    }
}
