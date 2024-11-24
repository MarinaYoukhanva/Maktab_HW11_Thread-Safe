package org.example.base.repository;

import org.example.base.model.BaseEntity;

import java.io.Serializable;
import java.sql.SQLException;

public interface BaseRepository<ID extends Serializable, T extends BaseEntity<ID>> {

    void save(T entity) throws SQLException;
}
