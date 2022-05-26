package com.talkon.talkon.services.user.user;


import com.talkon.talkon.dtos.responce.DataDto;
import com.talkon.talkon.dtos.user.user.*;
import com.talkon.talkon.services.base.BaseGenericService;
import org.springframework.http.ResponseEntity;

public interface UserService extends BaseGenericService
       {

    ResponseEntity<DataDto<SessionDto>>  getToken(LoginDto dto);

    void getCode(String phoneNumber);
}
