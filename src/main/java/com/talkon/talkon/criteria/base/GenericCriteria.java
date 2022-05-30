package com.talkon.talkon.criteria.base;

import lombok.*;
import org.springdoc.api.annotations.ParameterObject;

import java.io.Serializable;
import java.util.Objects;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class GenericCriteria implements BaseGenericCriteria, Serializable {

    protected Integer page;

    protected Integer size;

    public Integer getPage() {
        if (this.page == null || this.page < 1) {
            page = 1;
        }
        return page;
    }

    public Integer getSize() {
        if (Objects.isNull(this.size) || this.size < 1) {
            size = 10;
        }
        return size;
    }
}