package com.talkon.talkon.mappers.mentee;

import com.talkon.talkon.dtos.base.BaseGenericDto;
import com.talkon.talkon.dtos.mentee.MenteeDto;
import com.talkon.talkon.dtos.mentee.MenteeUpdateDto;
import com.talkon.talkon.entities.user.members.Mentee;
import com.talkon.talkon.mappers.base.AbstractMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MenteeMapper extends AbstractMapper<
        Mentee,
        MenteeDto,
        BaseGenericDto,
        MenteeUpdateDto
        > {
}