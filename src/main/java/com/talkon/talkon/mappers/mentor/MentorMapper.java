package com.talkon.talkon.mappers.mentor;

import com.talkon.talkon.dtos.mentor.MentorCreateDto;
import com.talkon.talkon.dtos.mentor.MentorDto;
import com.talkon.talkon.dtos.mentor.MentorUpdateDto;
import com.talkon.talkon.entities.user.members.Mentor;
import com.talkon.talkon.mappers.base.AbstractMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;


@Mapper(componentModel = "spring")
public interface MentorMapper extends AbstractMapper<Mentor, MentorDto, MentorCreateDto, MentorUpdateDto> {
}
