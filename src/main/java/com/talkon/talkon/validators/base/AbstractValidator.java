package com.talkon.talkon.validators.base;

import com.talkon.talkon.dtos.base.BaseGenericDto;
import com.talkon.talkon.dtos.base.GenericDto;

import javax.xml.bind.ValidationException;
import java.io.Serializable;

public abstract class AbstractValidator<CD extends BaseGenericDto, UD extends GenericDto, K extends Serializable> implements BaseGenericValidator {

    public abstract void validateKey(K id) throws ValidationException;

    public abstract void validOnCreate(CD cd) throws ValidationException;

    public abstract void validOnUpdate(UD cd) throws ValidationException;

}
