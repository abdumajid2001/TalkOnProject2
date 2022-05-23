package com.talkon.talkon.services.user.member.mentee;

import com.talkon.talkon.criteria.base.BaseGenericCriteria;
import com.talkon.talkon.criteria.base.GenericCriteria;
import com.talkon.talkon.dtos.base.BaseGenericDto;
import com.talkon.talkon.dtos.user.member.mentee.MenteeCreateDto;
import com.talkon.talkon.dtos.user.member.mentee.MenteeDto;
import com.talkon.talkon.dtos.user.member.mentee.MenteeUpdateDto;
import com.talkon.talkon.services.base.GenericCrudService;
import com.talkon.talkon.services.base.GenericService;

public interface MenteeService extends GenericCrudService<
        MenteeDto,
        MenteeCreateDto,
        MenteeUpdateDto,
        String,
        GenericCriteria
        > {
}
