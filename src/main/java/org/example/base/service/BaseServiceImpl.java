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
    public void save(T entity){
        try {
            repository.save(entity);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(T entity){
        try {
            repository.update(entity);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(ID id) {
        try {
            repository.delete(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<T> findById(ID id) {
        try {
            return repository.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
