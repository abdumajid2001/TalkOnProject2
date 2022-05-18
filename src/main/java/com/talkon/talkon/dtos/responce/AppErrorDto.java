package com.talkon.talkon.dtos.responce;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppErrorDto {

    private Timestamp timestamp;
    private Integer status;
    private String code;
    private String message;
    private String developerMessage;
    private String path;



    public AppErrorDto(String message, WebRequest webRequest, HttpStatus httpStatus, String developerMessage) {
        this(message, ((ServletWebRequest) webRequest).getRequest().getRequestURI(), httpStatus, developerMessage);
    }

    public AppErrorDto(String message, String path, HttpStatus httpStatus, String developerMessage) {
        this.developerMessage = developerMessage;
        this.timestamp = Timestamp.valueOf(LocalDateTime.now());
        this.status = httpStatus.value();
        this.code = httpStatus.getReasonPhrase();
        this.message = message;
        this.path = path;
    }


    @Builder
    public AppErrorDto(HttpStatus status, String message, String developerMessage, String path) {
        this.developerMessage = developerMessage;
        this.timestamp = Timestamp.valueOf(LocalDateTime.now());
        this.status = status.value();
        this.code = status.getReasonPhrase();
        this.message = message;
        this.path = path;
    }

    public AppErrorDto(String message, String developerMessage) {
        this.timestamp = Timestamp.valueOf(LocalDateTime.now());
        this.message = message;
        this.developerMessage = developerMessage;
    }
}
