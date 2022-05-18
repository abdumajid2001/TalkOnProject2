package com.talkon.talkon.services.user;


import com.talkon.talkon.criteria.base.BaseGenericCriteria;
import com.talkon.talkon.dtos.user.LoginDto;
import com.talkon.talkon.dtos.user.UserCreateDto;
import com.talkon.talkon.dtos.user.UserDto;
import com.talkon.talkon.dtos.user.UserUpdateDto;
import com.talkon.talkon.services.base.GenericCrudService;

public interface UserService extends GenericCrudService<
        UserDto,
        UserCreateDto,
        UserUpdateDto,
        String,
        BaseGenericCriteria
        > {

    String login(LoginDto dto);
}
