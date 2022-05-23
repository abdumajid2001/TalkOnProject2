package com.talkon.talkon.validators.user.member.mentee;

import com.talkon.talkon.dtos.base.BaseGenericDto;
import com.talkon.talkon.dtos.user.member.mentee.MenteeUpdateDto;
import com.talkon.talkon.exceptions.ValidationException;
import com.talkon.talkon.validators.base.AbstractValidator;

public class MenteeRepository extends AbstractValidator<
        BaseGenericDto,
        MenteeUpdateDto,
        String
        > {

    @Override
    public void validateKey(String id) throws ValidationException {

    }

    @Override
    public void validOnCreate(BaseGenericDto baseGenericDto) throws ValidationException {

    }

    @Override
    public void validOnUpdate(MenteeUpdateDto cd) throws ValidationException {

    }
}
