package com.javamsdt.library.util.constant.query;

public class UserQuery {
    public final static String INSERT_USER = "INSERT INTO user_table(first_name, last_name, user_email,user_login, user_password) VALUES(?,?,?,?,?)";

    public final static String UPDATE_USER_DATA = "UPDATE user_table SET first_name = ?, last_name = ?, user_email = ?, " +
            " user_login = ?, user_password = ?, user_role = ?, user_blocked = ? WHERE user_id = CAST( ? AS BIGINT)";

    public final static String UPDATE_USER = "UPDATE user_table SET first_name = ?, last_name = ?, user_email = ?, " +
            " user_login = ?, user_role = ARRAY[?]::role[], user_blocked = ?::boolean WHERE user_id = CAST( ? AS BIGINT)";

    public final static String UPDATE_FIRST_NAME = "UPDATE user_table SET first_name = ? WHERE user_id = CAST( ? AS BIGINT)";
    public final static String UPDATE_USER_LAST_NAME = "UPDATE user_table SET last_name = ? WHERE user_id = CAST( ? AS BIGINT)";
    public final static String UPDATE_USER_EMAIL = "UPDATE user_table SET user_email = ? WHERE user_id = CAST( ? AS BIGINT)";
    public final static String UPDATE_USER_LOGIN = "UPDATE user_table SET user_login = ? WHERE user_id = CAST( ? AS BIGINT)";
    public final static String UPDATE_USER_PASSWORD = "UPDATE user_table SET user_password = ? WHERE user_id = CAST( ? AS BIGINT)";
    public final static String UPDATE_USER_ROLE = "UPDATE user_table SET user_role = ARRAY[?]::role[] WHERE user_id = CAST( ? AS BIGINT)";
    public final static String UPDATE_USER_BLOCKED = "UPDATE user_table SET user_blocked = ? WHERE user_id = CAST( ? AS BIGINT)";


    public final static String SELECT_USER_BY_ID = "SELECT * FROM user_table WHERE user_id = CAST( ? AS BIGINT)";
    public final static String SELECT_USER_BY_NAME = "SELECT * FROM user_table WHERE first_name = ?";
    public final static String SELECT_USER_BY_LAST_NAME = "SELECT * FROM user_table WHERE last_name = ?";
    public final static String SELECT_USER_BY_EMAIL = "SELECT * FROM user_table WHERE user_email = ?";
    public final static String SELECT_USER_BY_LOGIN = "SELECT * FROM user_table WHERE user_login = ?";
    public final static String SELECT_USER_BY_PASSWORD = "SELECT * FROM user_table WHERE user_password = ?";
    public final static String SELECT_USER_BY_ROLE = "SELECT * FROM user_table WHERE user_role = ARRAY[?]::role[]";
    public final static String SELECT_USER_BY_USER_BLOCKED = "SELECT * FROM user_table WHERE user_blocked = ?";
    public final static String SELECT_USER_BY_LOGIN_PASSWORD = "SELECT * FROM user_table WHERE user_login = ? AND user_password = ?";

    public final static String DELETE_USER = "DELETE FROM user_table WHERE user_id = CAST( ? AS BIGINT)";

    // Admin Query
    public final static String SELECT_ALL_USERS = "SELECT * FROM user_table";
    public final static String SELECT_ALL_SORTING_BY_NAME_FOR_ADMIN = "SELECT * FROM user_table ORDER BY first_name ASC";
    public final static String SELECT_ALL_SORTING_BY_EMAIL_FOR_ADMIN = "SELECT * FROM user_table ORDER BY user_email ASC";


    // Librarian Query
    public final static String SELECT_USER_BY_READER_ROLE = "SELECT * FROM user_table WHERE user_role = '{READER}'";
    public final static String SELECT_ALL_USERS_SORTING_BY_NAME = "SELECT * FROM user_table WHERE user_role = '{READER}' ORDER BY first_name ASC";
    public final static String SELECT_ALL_USERS_SORTING_BY_EMAIL = "SELECT * FROM user_table WHERE user_role = '{READER}' ORDER BY user_email ASC";


}
