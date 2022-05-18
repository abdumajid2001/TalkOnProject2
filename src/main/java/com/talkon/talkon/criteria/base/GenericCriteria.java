package com.talkon.talkon.criteria.base;

import lombok.*;
import org.springdoc.api.annotations.ParameterObject;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ParameterObject
public class GenericCriteria implements BaseGenericCriteria, Serializable {

    protected Long selfId;

    protected Integer page;

    protected Integer perPage;

    protected String sortBy;

    protected String sortDirection;

    public String getSortDirection() {
        return sortDirection == null || sortDirection.equals("") ? " ASC " : sortDirection;
    }
}