package com.talkon.talkon.mappers.user.user;

import com.talkon.talkon.dtos.user.member.mentee.MenteeCreateDto;
import com.talkon.talkon.dtos.user.member.mentee.MenteeUpdateDto;
import com.talkon.talkon.dtos.user.member.mentor.MentorUpdateDto;
import com.talkon.talkon.dtos.user.user.UserCreateDto;
import com.talkon.talkon.dtos.user.user.UserUpdateDto;
import com.talkon.talkon.entities.user.User;
import com.talkon.talkon.mappers.base.AbstractMapper;
import com.talkon.talkon.dtos.user.user.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper extends AbstractMapper<User, UserDto, UserCreateDto, UserUpdateDto> {

    User fromUpdateDto(UserUpdateDto dto, @MappingTarget User user);

    User fromCreateDto(MenteeCreateDto dto, @MappingTarget User user);

    User fromUpdateDto(MenteeUpdateDto dto);

    User fromUpdateDto(MenteeUpdateDto dto, @MappingTarget User user);
}
