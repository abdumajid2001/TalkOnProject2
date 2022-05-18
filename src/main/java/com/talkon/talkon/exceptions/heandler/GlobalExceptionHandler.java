package com.talkon.talkon.exceptions.heandler;

import com.talkon.talkon.dtos.responce.AppErrorDto;
import com.talkon.talkon.dtos.responce.DataDto;
import com.talkon.talkon.exceptions.user.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<DataDto<AppErrorDto>> handlerUserNotFoundException(UserNotFoundException exception, WebRequest request) {
        return new ResponseEntity<>(
                DataDto.<AppErrorDto>
                                builder()
                        .success(false)
                        .appErrorDto(
                                new AppErrorDto(
                                        exception.getMessage(),
                                        request,
                                        HttpStatus.METHOD_NOT_ALLOWED,
                                        exception.getDeveloperMessage()
                                )
                        )
                        .build(), HttpStatus.NOT_FOUND);
    }

}
