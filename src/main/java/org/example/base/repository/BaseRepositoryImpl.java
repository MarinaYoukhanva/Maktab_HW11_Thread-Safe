package org.example.base.repository;

import lombok.RequiredArgsConstructor;
import org.example.base.model.BaseEntity;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@RequiredArgsConstructor
public abstract class BaseRepositoryImpl<ID extends Serializable, T extends BaseEntity<ID>>
        implements BaseRepository<ID, T> {
    
    private final Connection connection;

    private final String INSERT_SQL = " INSERT INTO " + " " + getTableName() + " " + getColumnName() +
            " VALUES " + getColumnCount();

    @Override
    public void save(T entity) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(INSERT_SQL);
        fillParamsForStatement(statement, entity, false);
        statement.execute();
        statement.close();
    }

    public abstract String getTableName();
    public abstract String getColumnName();
    public abstract String getColumnCount();
    public abstract void fillParamsForStatement(PreparedStatement statement,
                                                T entity,
                                                Boolean isCountOnly) throws SQLException;
}
