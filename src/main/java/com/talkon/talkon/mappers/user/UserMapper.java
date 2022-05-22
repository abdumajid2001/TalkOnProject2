package com.talkon.talkon.mappers.user;

import com.talkon.talkon.dtos.user.UserCreateDto;
import com.talkon.talkon.dtos.user.UserUpdateDto;
import com.talkon.talkon.entities.user.User;
import com.talkon.talkon.mappers.base.AbstractMapper;
import com.talkon.talkon.dtos.user.UserDto;
import org.hibernate.annotations.Target;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;


@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper extends AbstractMapper<User, UserDto, UserCreateDto, UserUpdateDto> {

    User fromUpdateDto(UserUpdateDto dto,@MappingTarget User user);

}
