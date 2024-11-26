package org.example.base.service;

import org.example.base.model.BaseEntity;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Optional;

public interface BaseService<ID extends Serializable, T extends BaseEntity<ID>> {

    void save(T entity) ;
    void update(T entity);
    void delete(ID id) ;
    Optional<T> findById(ID id) ;
}
