package org.example.repository;

import org.example.base.repository.BaseRepository;
import org.example.entity.User;

import java.sql.SQLException;

public interface UserRepository extends BaseRepository<Integer, User> {

    void initTable() throws SQLException;

}
