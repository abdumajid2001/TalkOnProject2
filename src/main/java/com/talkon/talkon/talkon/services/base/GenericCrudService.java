package com.talkon.talkon.talkon.services.base;

import com.talkon.talkon.talkon.criteria.base.BaseGenericCriteria;
import com.talkon.talkon.talkon.dtos.base.BaseGenericDto;
import com.talkon.talkon.talkon.dtos.base.GenericDto;

import java.io.Serializable;

public interface GenericCrudService<
        D extends GenericDto,
        CD extends BaseGenericDto,
        UD extends GenericDto,
        K extends Serializable,
        C extends BaseGenericCriteria
        > extends GenericService<D, K, C> {
    K create(CD dto);

    void delete(K id);

    void update(UD dto);
}
