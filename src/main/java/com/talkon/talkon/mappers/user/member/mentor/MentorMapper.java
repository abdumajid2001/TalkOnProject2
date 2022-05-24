package com.talkon.talkon.mappers.user.member.mentor;

import com.talkon.talkon.dtos.user.member.mentor.MentorCreateDto;
import com.talkon.talkon.dtos.user.member.mentor.MentorDto;
import com.talkon.talkon.dtos.user.member.mentor.MentorUpdateDto;
import com.talkon.talkon.entities.user.members.Mentor;
import com.talkon.talkon.mappers.base.AbstractMapper;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface MentorMapper extends AbstractMapper<Mentor, MentorDto, MentorCreateDto, MentorUpdateDto> {
}
