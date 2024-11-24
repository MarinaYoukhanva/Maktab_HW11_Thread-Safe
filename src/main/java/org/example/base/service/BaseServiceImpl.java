package org.example.base.service;

import lombok.RequiredArgsConstructor;
import org.example.base.model.BaseEntity;
import org.example.base.repository.BaseRepository;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Optional;

@RequiredArgsConstructor
public class BaseServiceImpl<ID extends Serializable, T extends BaseEntity<ID>,
        R extends BaseRepository<ID, T>> implements BaseService<ID, T>{
    private final R repository;

    @Override
    public void save(T entity) throws SQLException {
        repository.save(entity);
    }

    @Override
    public void update(T entity) throws SQLException {
        repository.update(entity);
    }

    @Override
    public void delete(ID id) throws SQLException {
        repository.delete(id);
    }

    @Override
    public Optional<T> findById(ID id) throws SQLException {
        return repository.findById(id);
    }
}
