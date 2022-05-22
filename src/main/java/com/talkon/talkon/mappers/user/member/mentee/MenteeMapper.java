package com.talkon.talkon.mappers.user.member.mentee;

import com.talkon.talkon.dtos.base.BaseGenericDto;
import com.talkon.talkon.dtos.user.member.mentee.MenteeCreateDto;
import com.talkon.talkon.dtos.user.member.mentee.MenteeDto;
import com.talkon.talkon.dtos.user.member.mentee.MenteeUpdateDto;
import com.talkon.talkon.entities.user.members.Mentee;
import com.talkon.talkon.mappers.base.AbstractMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MenteeMapper extends AbstractMapper<
        Mentee,
        MenteeDto,
        MenteeCreateDto,
        MenteeUpdateDto
        > {
}
