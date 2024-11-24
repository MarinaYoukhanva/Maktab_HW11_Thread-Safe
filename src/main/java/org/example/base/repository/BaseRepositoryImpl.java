package org.example.base.repository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.base.model.BaseEntity;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Getter
@RequiredArgsConstructor
public abstract class BaseRepositoryImpl<ID extends Serializable, T extends BaseEntity<ID>>
        implements BaseRepository<ID, T> {

    private final Connection connection;

    private final String INSERT_SQL = " INSERT INTO " + " " + getTableName() + " " + getColumnName() +
            " VALUES " + getColumnCount();

    private final String UPDATE_SQL = " UPDATE " + getTableName() + " SET " + getColumnsToUpdate() + " WHERE id = ? ";

    private final String DELETE_SQL = " DELETE FROM " + getTableName() + " WHERE id = ? ";

    private final String FIND_BY_ID_SQL = " SELECT * FROM " + getTableName() + " WHERE id = ? ";

    @Override
    public void save(T entity) throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement(INSERT_SQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
            fillParamsForStatement(statement, entity, false);
            statement.execute();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next())
                    entity.setId((((ID) (Serializable) generatedKeys.getInt(1))));
            }
        }
    }

    @Override
    public void update(T entity) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_SQL)) {
            fillParamsForStatement(statement, entity, false);
            statement.setInt(getColumnCountNum() + 1, (int) entity.getId());
            statement.execute();
        }
    }

    @Override
    public void delete(ID id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_SQL)) {
            statement.setInt(1, (int) id);
            statement.execute();
        }
    }

    @Override
    public Optional<T> findById(ID id) throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            statement.setInt(1, (int) id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                T entity = getParamsFromResultSet(resultSet);
                return Optional.of(entity);
            }
        }
        return Optional.empty();
    }

    public abstract String getTableName();

    public abstract String getColumnName();

    public abstract String getColumnCount();

    public abstract int getColumnCountNum();

    public abstract String getColumnsToUpdate();

    public abstract void fillParamsForStatement(PreparedStatement statement,
                                                T entity,
                                                Boolean isCountOnly) throws SQLException;

    public abstract T getParamsFromResultSet(ResultSet resultSet) throws SQLException;
}
