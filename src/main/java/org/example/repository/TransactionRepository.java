package org.example.repository;

import org.example.base.repository.BaseRepository;
import org.example.entity.Transaction;

import java.sql.SQLException;

public interface TransactionRepository extends BaseRepository<Integer, Transaction> {

    public void initTable() throws SQLException;

}


