package com.epam.library.model.dao.query;

public class UserQuery {
    public final static String INSERT_USER = "INSERT INTO user_table(name, last_name, email, login, password) VALUES(?,?,?,?,?)";

    public final static String UPDATE_USER_DATA = "UPDATE user_table SET name = ?, last_name = ?, email = ?, " +
            " login = ?, password = ?, role = ?, blocked = ? WHERE id = ?";

    public final static String UPDATE_USER_NAME = "UPDATE user_table SET name = ? WHERE id = ?";
    public final static String UPDATE_USER_LAST_NAME = "UPDATE user_table SET last_name = ? WHERE id = ?";
    public final static String UPDATE_USER_EMAIL = "UPDATE user_table SET email = ? WHERE id = ?";
    public final static String UPDATE_USER_LOGIN = "UPDATE user_table SET login = ? WHERE id = ?";
    public final static String UPDATE_USER_PASSWORD = "UPDATE user_table SET password = ? WHERE id = ?";
    public final static String UPDATE_USER_ROLE = "UPDATE user_table SET role = ? WHERE id = ?";
    public final static String UPDATE_USER_BLOCKED = "UPDATE user_table SET blocked = ? WHERE id = ?";


    public final static String SELECT_USER_BY_ID = "SELECT * FROM user_table WHERE id = ?";
    public final static String SELECT_USER_BY_NAME = "SELECT * FROM user_table WHERE name = ?";
    public final static String SELECT_USER_BY_LAST_NAME = "SELECT * FROM user_table WHERE last_name = ?";
    public final static String SELECT_USER_BY_EMAIL = "SELECT * FROM user_table WHERE email = ?";
    public final static String SELECT_USER_BY_LOGIN = "SELECT * FROM user_table WHERE login = ?";
    public final static String SELECT_USER_BY_PASSWORD = "SELECT * FROM user_table WHERE password = ?";
    public final static String SELECT_USER_BY_ROLE = "SELECT * FROM user_table WHERE role = ?";
    public final static String SELECT_USER_BY_USER_BLOCKED = "SELECT * FROM user_table WHERE blocked = ?";
    public final static String SELECT_USER_BY_LOGIN_PASSWORD = "SELECT * FROM user_table WHERE login = ? AND password = ?";

    public final static String DELETE_USER = "DELETE FROM user_table WHERE id= ?";

    // Admin Query
    public final static String SELECT_ALL_USERS = "SELECT * FROM user_table";
    public final static String SELECT_ALL_SORTING_BY_NAME_FOR_ADMIN = "SELECT * FROM user_table ORDER BY name ASC";


    // Librarian Query
    public final static String SELECT_USER_BY_READER_ROLE = "SELECT * FROM user_table WHERE role = 'READER'";
    public final static String SELECT_ALL_USERS_SORTING_BY_NAME = "SELECT * FROM user_table WHERE role = 'READER' ORDER BY name ASC";
    public final static String SELECT_ALL_USERS_SORTING_BY_EMAIL = "SELECT * FROM user_table WHERE role = 'READER' ORDER BY email ASC";


}
