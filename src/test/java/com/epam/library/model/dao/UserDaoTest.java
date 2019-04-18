package com.epam.library.model.dao;

import com.epam.library.entity.User;
import com.epam.library.entity.enumeration.Role;
import com.epam.library.model.db.ConnectionPool;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.util.Optional;

public class UserDaoTest {
    private static Connection connection;
    private static UserDao userDao;
    private static User user;

    @BeforeClass
    public static void init(){
        connection = ConnectionPool.getInstance().getConnection();
        userDao = new UserDao(connection);
    }

    @Test
    public void getByIdPass() throws DaoException {
        user = new User(1, "Ahmed","Samy","serenitydiver@hotmail.com", "JAVAMSDT",
                "java", Role.ADMIN, false);
        long id = 1;
        Optional<User> userOptional = userDao.getById(1);


    }
}
