package org.example.base.service;

import org.example.base.model.BaseEntity;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Optional;

public interface BaseService<ID extends Serializable, T extends BaseEntity<ID>> {

    void save(T entity) throws SQLException;
    void update(T entity) throws SQLException;
    void delete(ID id) throws SQLException;
    Optional<T> findById(ID id) throws SQLException;
}
