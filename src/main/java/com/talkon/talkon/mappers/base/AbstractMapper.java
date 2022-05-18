package com.talkon.talkon.mappers.base;

import com.talkon.talkon.dtos.base.BaseGenericDto;
import com.talkon.talkon.dtos.base.GenericDto;
import com.talkon.talkon.entities.base.BaseGenericEntity;

import java.util.List;

public interface AbstractMapper<
        E extends BaseGenericEntity,
        D extends GenericDto,
        CD extends BaseGenericDto,
        UP extends GenericDto
        > extends BaseGenericMapper {
    D toDto(E entity);

    List<D> toDto(List<E> entities);

    E fromCreateDto(CD createDto);

    E fromUpdateDto(UP updateDto);
}
