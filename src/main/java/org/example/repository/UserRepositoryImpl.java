package org.example.repository;

import org.example.base.repository.BaseRepositoryImpl;
import org.example.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class UserRepositoryImpl extends BaseRepositoryImpl<Integer, User>
        implements UserRepository {

    public UserRepositoryImpl(Connection connection) throws SQLException {
        super(connection);
        initTable();
    }

    @Override
    public String getTableName() {
        return " \"user\" ";
    }

    @Override
    public String getColumnName() {
        return " ( username , password ) ";
    }

    @Override
    public String getColumnCount() {
        return " (? , ?) ";
    }

    @Override
    public int getColumnCountNum() {
        return 2;
    }

    @Override
    public String getColumnsToUpdate() {
        return "  username = ?, password = ?  ";
    }

    @Override
    public void fillParamsForStatement(PreparedStatement statement, User entity, Boolean isCountOnly) throws SQLException {
        statement.setString(1, entity.getUsername());
        statement.setString(2, entity.getPassword());
    }

    @Override
    public User getParamsFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(1);
        LocalDateTime createdAt = resultSet.getTimestamp(2).toLocalDateTime();
        String username = resultSet.getString(3);
        String password = resultSet.getString(4);
        return new User(id,createdAt, username, password);
    }

    private static final String CREATE_TABLE = """
            CREATE TABLE IF NOT EXISTS "user"
            (
                id SERIAL PRIMARY KEY,
                createdAt TIMESTAMP NOT NULL DEFAULT (current_timestamp),
                username VARCHAR(50) UNIQUE NOT NULL,
                password VARCHAR(50) NOT NULL
            )
            """;

    @Override
    public void initTable() throws SQLException {
        try(PreparedStatement preparedStatement = super.getConnection().prepareStatement(CREATE_TABLE)){
            preparedStatement.execute();
        }
    }
}
