package com.talkon.talkon.validators.user;

import com.talkon.talkon.dtos.user.UserCreateDto;
import com.talkon.talkon.dtos.user.UserUpdateDto;
import com.talkon.talkon.validators.base.AbstractValidator;
import org.springframework.stereotype.Component;

import javax.xml.bind.ValidationException;

@Component
public class UserValidator extends AbstractValidator<UserCreateDto, UserUpdateDto, String> {
    @Override
    public void validateKey(String id) throws ValidationException {

    }

    @Override
    public void validOnCreate(UserCreateDto userCreateDto) throws ValidationException {

    }

    @Override
    public void validOnUpdate(UserUpdateDto cd) throws ValidationException {

    }
}
