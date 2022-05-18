package com.talkon.talkon.talkon.services.base;

import com.talkon.talkon.talkon.criteria.base.BaseGenericCriteria;
import com.talkon.talkon.talkon.dtos.base.BaseGenericDto;

import java.io.Serializable;
import java.util.List;

public interface GenericService<
        D extends BaseGenericDto,
        K extends Serializable,
        C extends BaseGenericCriteria
        > extends BaseGenericService {

    D get(K id);

    List<D> getAll(C criteria);

}
