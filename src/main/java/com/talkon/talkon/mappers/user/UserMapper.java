package com.talkon.talkon.mappers.user;

import com.talkon.talkon.dtos.user.UserCreateDto;
import com.talkon.talkon.dtos.user.UserUpdateDto;
import com.talkon.talkon.entities.user.User;
import com.talkon.talkon.mappers.base.AbstractMapper;
import com.talkon.talkon.dtos.user.UserDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;


@Mapper(componentModel = "spring")
public interface UserMapper extends AbstractMapper<User, UserDto, UserCreateDto, UserUpdateDto> {
}
