package com.talkon.talkon.services.user.user;


import com.talkon.talkon.dtos.responce.DataDto;
import com.talkon.talkon.criteria.base.BaseGenericCriteria;
import com.talkon.talkon.dtos.user.user.*;
import com.talkon.talkon.services.base.GenericCrudService;
import org.springframework.http.ResponseEntity;

public interface UserService extends GenericCrudService<
        UserDto,
        UserCreateDto,
        UserUpdateDto,
        String,
        BaseGenericCriteria
        > {

    ResponseEntity<DataDto<SessionDto>>  getToken(LoginDto dto);

    void getCode(String phoneNumber);
}
