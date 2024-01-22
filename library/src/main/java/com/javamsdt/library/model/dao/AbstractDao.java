package com.javamsdt.library.model.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.javamsdt.library.model.builder.Builder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<T> implements IDao<T> {

    private Connection connection;

    public AbstractDao(Connection connection) {
        this.connection = connection;
    }

    public AbstractDao() {

    }

    public Connection getConnection() {
        return connection;
    }

    protected List<T> executeQuery(String query, Builder<T> builder, String... parameters) throws DaoException {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        List<T> entity = new ArrayList<>();
        try {

            preparedStatement = connection.prepareStatement(query);
            prepareStatement(preparedStatement, parameters);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                T build = builder.build(resultSet);
                entity.add(build);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return entity;
    }


    protected Optional<T> executeSingleResponseQuery(String query, Builder<T> builder, String... parameters) throws DaoException {
        List<T> list = executeQuery(query, builder, parameters);
        if (list.size() == 1) {
            return Optional.of(list.get(0));
        } else {
            return Optional.empty();
        }
    }

    protected void executeUpdate(String query, String... parameters) throws DaoException {
        PreparedStatement preparedStatement;

        try {

            preparedStatement = connection.prepareStatement(query);
            prepareStatement(preparedStatement, parameters);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    protected void prepareStatement(PreparedStatement statement, String... parameters) throws SQLException {
        for (int i = 1; i < parameters.length + 1; i++) {
            statement.setObject(i, parameters[i - 1]);
        }
    }


}
