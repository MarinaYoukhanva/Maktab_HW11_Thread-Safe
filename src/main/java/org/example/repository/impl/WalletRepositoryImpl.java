package org.example.repository.impl;

import org.example.base.repository.BaseRepositoryImpl;
import org.example.entity.User;
import org.example.entity.Wallet;
import org.example.repository.UserRepository;
import org.example.repository.WalletRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;

public class WalletRepositoryImpl extends BaseRepositoryImpl<Integer, Wallet>
        implements WalletRepository {

    UserRepository userRepository = new UserRepositoryImpl(super.getConnection());

    public WalletRepositoryImpl(Connection connection) throws SQLException {
        super(connection);
        initTable();
    }

    @Override
    public String getTableName() {
        return " wallet ";
    }

    @Override
    public String getColumnName() {
        return " ( address , balance , user_id ) ";
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
        return " address = ?, balance = ?, user_id = ? ";
    }

    @Override
    public void fillParamsForStatement(PreparedStatement statement, Wallet entity, Boolean isCountOnly) throws SQLException {
        statement.setString(1,entity.getAddress());
        statement.setDouble(2,entity.getBalance());
        statement.setInt(3,entity.getUser().getId());
    }

    @Override
    public Wallet getParamsFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(1);
        LocalDateTime createdAt = resultSet.getTimestamp(2).toLocalDateTime();
        String address = resultSet.getString(3);
        double balance = resultSet.getDouble(4);
        int userId = resultSet.getInt(5);
        Optional<User> user =  userRepository.findById(userId);
        return new Wallet(id, createdAt, address, balance, user.get());
    }

    private static final String CREATE_TABLE = """
            CREATE TABLE IF NOT EXISTS wallet
            (
                id SERIAL PRIMARY KEY,
                created_at TIMESTAMP NOT NULL DEFAULT (current_timestamp),
                address CHAR(10) UNIQUE NOT NULL,
                balance DOUBLE PRECISION DEFAULT (0),
                user_id INT NOT NULL REFERENCES "user"(id)
            )
            """;

    @Override
    public void initTable() throws SQLException {
        try(PreparedStatement preparedStatement = super.getConnection().prepareStatement(CREATE_TABLE)){
            preparedStatement.execute();
        }

    }
}
