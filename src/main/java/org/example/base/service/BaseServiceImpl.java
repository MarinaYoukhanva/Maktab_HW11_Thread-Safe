package org.example.base.service;

import lombok.RequiredArgsConstructor;
import org.example.base.model.BaseEntity;
import org.example.base.repository.BaseRepository;

import java.io.Serializable;
import java.sql.SQLException;

@RequiredArgsConstructor
public class BaseServiceImpl<ID extends Serializable, T extends BaseEntity<ID>,
        R extends BaseRepository<ID, T>> implements BaseService<ID, T>{
    private final R repository;

    @Override
    public void save(T entity) throws SQLException {
        repository.save(entity);
    }
}
