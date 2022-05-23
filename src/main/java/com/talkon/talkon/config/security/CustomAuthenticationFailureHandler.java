//package com.talkon.talkon.config.security;
//
//import com.talkon.talkon.dtos.responce.AppErrorDto;
//import com.talkon.talkon.dtos.responce.DataDto;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Component
//@RequiredArgsConstructor
//public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
//
//
//    private final ObjectMapper mapper;
//
//    @Override
//    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
//        mapper.writeValue(response.getOutputStream(),
//                new ResponseEntity<>(
//                        DataDto.<AppErrorDto>
//                                        builder()
//                                .success(false)
//                                .appErrorDto(
//                                        new AppErrorDto(
//                                                exception.getMessage(),
//                                                "Login qila olmadingiz"
//                                        )
//                                )
//                                .build(), HttpStatus.NOT_FOUND));
//
//    }
//
//}
