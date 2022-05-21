package com.talkon.talkon.validators.mentor;

import com.talkon.talkon.dtos.mentor.MentorCreateDto;
import com.talkon.talkon.dtos.mentor.MentorUpdateDto;
import com.talkon.talkon.validators.base.AbstractValidator;
import org.springframework.stereotype.Component;

import javax.xml.bind.ValidationException;
@Component
public class MentorValidation extends AbstractValidator<MentorCreateDto, MentorUpdateDto, String> {
    @Override
    public void validateKey(String id) throws ValidationException {

    }

    @Override
    public void validOnCreate(MentorCreateDto mentorCreateDto) throws ValidationException {

    }

    @Override
    public void validOnUpdate(MentorUpdateDto cd) throws ValidationException {

    }
}
