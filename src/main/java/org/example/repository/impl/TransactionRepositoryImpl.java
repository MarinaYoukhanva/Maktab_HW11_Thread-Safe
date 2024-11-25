package org.example.repository.impl;

import org.example.base.repository.BaseRepositoryImpl;
import org.example.entity.Transaction;
import org.example.entity.TransactionType;
import org.example.entity.Wallet;
import org.example.repository.TransactionRepository;
import org.example.repository.WalletRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;

public class TransactionRepositoryImpl extends BaseRepositoryImpl<Integer, Transaction>
        implements TransactionRepository {

    WalletRepository walletRepository = new WalletRepositoryImpl(super.getConnection());


    public TransactionRepositoryImpl(Connection connection) throws SQLException {
        super(connection);
        initTable();
    }

    @Override
    public String getTableName() {
        return " transaction ";
    }

    @Override
    public String getColumnName() {
        return " ( amount , type , wallet_id ) ";
    }

    @Override
    public String getColumnCount() {
        return " (? , ? , ?) ";
    }

    @Override
    public int getColumnCountNum() {
        return 3;
    }

    @Override
    public String getColumnsToUpdate() {
        return " amount = ? , type = ? , wallet_id = ? ";
    }

    @Override
    public void fillParamsForStatement(PreparedStatement statement, Transaction entity, Boolean isCountOnly) throws SQLException {
        statement.setDouble(1, entity.getAmount());
        statement.setString(2, entity.getType().toString());
        statement.setInt(3, entity.getWallet().getId());
    }

    @Override
    public Transaction getParamsFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(1);
        LocalDateTime createdAt = resultSet.getTimestamp(2).toLocalDateTime();
        Double amount = resultSet.getDouble(3);
        String type = resultSet.getString(4);
        int walletId = resultSet.getInt(5);
        Optional<Wallet> wallet = walletRepository.findById(walletId);
        return new Transaction(id, createdAt, amount, TransactionType.valueOf(type), wallet.get());
    }

    private static final String CREATE_TABLE = """
            CREATE TABLE IF NOT EXISTS transaction
            (
                id SERIAL PRIMARY KEY,
                created_at TIMESTAMP NOT NULL DEFAULT (current_timestamp),
                amount DOUBLE PRECISION NOT NULL,
                type VARCHAR (10) NOT NULL,
                wallet_id INT NOT NULL REFERENCES wallet(id)
            )
            """;

    @Override
    public void initTable() throws SQLException {
        try(PreparedStatement preparedStatement = super.getConnection().prepareStatement(CREATE_TABLE)){
            preparedStatement.execute();
        }
    }
}
