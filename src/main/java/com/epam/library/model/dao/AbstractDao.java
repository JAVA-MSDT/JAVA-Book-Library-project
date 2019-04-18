package com.epam.library.model.dao;

import com.epam.library.model.builder.Builder;
import com.epam.library.model.db.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<T> implements IDao<T>  {
private final static Logger logger = LogManager.getLogger();

private Connection connection;

    public AbstractDao(Connection connection){
        this.connection = connection;
    }

    public AbstractDao(){

    }

    protected List<T> executeQuery(String query, Builder<T> builder, String... parameters ) throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<T> entity = new ArrayList<>();
        try {

            preparedStatement = connection.prepareStatement(query);
            prepareStatement(preparedStatement, parameters);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                T build = builder.build(resultSet);
                entity.add(build);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }finally {
            closeResultSet(resultSet);
            closeStatement(preparedStatement);
            closeConnection(connection);
        }
        return entity;
    }


    protected Optional<T> executeSingleResponseQuery(String query, Builder<T> builder, String... parameters) throws DaoException {
        List<T> list = executeQuery(query, builder,parameters);
        if(list.size() == 1){
            return Optional.of(list.get(0));
        }else {
            return Optional.empty();
        }
    }

    protected void executeUpdate(String query, String... parameters) throws DaoException {
        PreparedStatement preparedStatement = null;

        try {

            preparedStatement = connection.prepareStatement(query);
            prepareStatement(preparedStatement, parameters);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }finally {
            closeStatement(preparedStatement);
            closeConnection(connection);
        }
    }
    private void prepareStatement(PreparedStatement statement, String... parameters) throws SQLException {
        for(int i = 1; i < parameters.length + 1; i++){
            statement.setObject(i, parameters[i - 1]);
        }
    }

    private void closeStatement(Statement statement){
        if(statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                logger.log(Level.ERROR,"unable to close statement");
            }
        }
    }

    private void closeConnection(Connection connection){
        if(connection != null){
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    private void closeResultSet(ResultSet resultSet){
        if(resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                logger.log(Level.ERROR,"unable to close ResultSet");
            }
        }
    }

}
