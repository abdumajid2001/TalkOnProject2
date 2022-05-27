package com.talkon.talkon.validators.base;

import com.talkon.talkon.dtos.base.BaseGenericDto;
import com.talkon.talkon.dtos.base.GenericDto;
import com.talkon.talkon.exceptions.ValidationException;
import test2.User;

import java.io.Serializable;

public abstract class AbstractValidator<CD extends BaseGenericDto, UD extends GenericDto, K extends Serializable> implements BaseGenericValidator {


    public abstract void validateKey(K id) throws ValidationException;

    public abstract void validOnCreate(CD dto) throws ValidationException;

    public abstract void validOnUpdate(UD dto) throws ValidationException;

}
