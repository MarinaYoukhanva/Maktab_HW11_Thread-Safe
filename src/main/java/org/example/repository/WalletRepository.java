package org.example.repository;

import org.example.base.repository.BaseRepository;
import org.example.entity.Wallet;

import java.sql.SQLException;

public interface WalletRepository extends BaseRepository<Integer, Wallet> {

    public void initTable() throws SQLException;
}
