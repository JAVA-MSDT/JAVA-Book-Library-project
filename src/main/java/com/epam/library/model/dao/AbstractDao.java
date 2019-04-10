package com.epam.library.model.dao;

import com.epam.library.model.builder.Builder;
import com.epam.library.model.db.ConnectionPool;
import com.epam.library.model.db.ConnectionPoolException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<T> implements IDao<T>  {
private final static Logger logger = LogManager.getLogger();

    protected List<T> executeQuery(String query, Builder<T> builder){
        Connection connection = null;
        List<T> entities = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                T item = builder.build(resultSet);
                entities.add(item);
            }
        } catch (SQLException e) {
            e.getErrorCode();
        }
        return entities;
    }


    protected Optional<T> executeSingleResponsceQuery(String query, Builder<T> builder){
        List<T> list = executeQuery(query, builder);
        if(list.size() == 1){
            return Optional.of(list.get(0));
        }else {
            return Optional.empty();
        }
    }


    protected void closeStatement(Statement statement){
        if(statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                logger.log(Level.ERROR,"unable to close statement");
            }
        }
    }

    protected void closeConnection(Connection connection){
        if(connection != null){
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    protected void closeResultSet(ResultSet resultSet){
        if(resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                logger.log(Level.ERROR,"unable to close ResultSet");
            }
        }
    }


}
