package com.talkon.talkon.talkon.services.base;

import com.talkon.talkon.talkon.mappers.base.BaseGenericMapper;
import com.talkon.talkon.talkon.repositories.base.BaseGenericRepository;
import com.talkon.talkon.talkon.validators.base.BaseGenericValidator;

public abstract class AbstractService<
        R extends BaseGenericRepository,
        M extends BaseGenericMapper,
        V extends BaseGenericValidator
        > implements BaseGenericService {

    protected final M mapper;
    protected final V validator;
    protected final R repository;

    protected AbstractService(M mapper, V validator, R repository) {
        this.mapper = mapper;
        this.validator = validator;
        this.repository = repository;
    }

}
