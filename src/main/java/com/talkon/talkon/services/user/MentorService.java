package com.talkon.talkon.services.user;

import com.talkon.talkon.criteria.base.BaseGenericCriteria;
import com.talkon.talkon.dtos.mentor.MentorCreateDto;
import com.talkon.talkon.dtos.mentor.MentorDto;
import com.talkon.talkon.dtos.mentor.MentorUpdateDto;
import com.talkon.talkon.services.base.GenericCrudService;

public interface MentorService extends GenericCrudService<
        MentorDto,
        MentorCreateDto,
        MentorUpdateDto,
        String,
        BaseGenericCriteria
        > {
}
