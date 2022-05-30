package com.talkon.talkon.validators.user.user;

import com.talkon.talkon.dtos.user.user.UserCreateDto;
import com.talkon.talkon.dtos.user.user.UserUpdateDto;
import com.talkon.talkon.exceptions.ValidationException;
import com.talkon.talkon.validators.base.AbstractValidator;
import org.springframework.stereotype.Component;

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
