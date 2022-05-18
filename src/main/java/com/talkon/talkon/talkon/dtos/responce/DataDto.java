package com.talkon.talkon.talkon.dtos.responce;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DataDto<T> implements Serializable {

    protected T data;

    protected AppErrorDto appErrorDto;

    protected boolean success;

    private Long totalCount;


    public DataDto(T data){
        this.data = data;
        this.success = true;
    }

    public DataDto(AppErrorDto appErrorDto) {
        this.appErrorDto = appErrorDto;
        this.success = false;
    }

    public DataDto(T data, Long totalCount) {
        this.data = data;
        this.totalCount = totalCount;
        this.success = true;
    }
}
