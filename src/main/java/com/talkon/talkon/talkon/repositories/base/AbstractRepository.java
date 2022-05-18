package com.talkon.talkon.talkon.repositories.base;

import com.talkon.talkon.talkon.entities.base.BaseGenericEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface AbstractRepository<
        E extends BaseGenericEntity,
        K extends Serializable
        > extends BaseGenericRepository, JpaRepository<E,K> {

}
