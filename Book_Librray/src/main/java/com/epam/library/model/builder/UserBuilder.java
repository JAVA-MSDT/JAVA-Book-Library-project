package com.epam.library.model.builder;

import com.epam.library.entity.User;
import com.epam.library.entity.enumeration.Role;
import com.epam.library.util.EnumService;
import com.epam.library.util.constant.entityconstant.UserConstant;
import com.epam.library.util.validate.ArgumentValidator;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author (Ahmed Samy)
 * @Email serenitydiver@hotmail.com
 */
public class UserBuilder implements Builder<User> {

    /**
     * @param resultSet which has the info of the user
     * @return user after extracting the date from the data base.
     * @throws SQLException
     */
    @Override
    public User build(ResultSet resultSet) throws SQLException {
        ArgumentValidator.checkForNull(resultSet, "Not allow for null Result set in UserBuilder");

        long id = resultSet.getLong(UserConstant.ID);
        String name = resultSet.getString(UserConstant.NAME);
        String lastName = resultSet.getString(UserConstant.LAST_NAME);
        String email = resultSet.getString(UserConstant.EMAIL);
        String login = resultSet.getString(UserConstant.LOGIN);
        String password = resultSet.getString(UserConstant.PASSWORD);
        Role role = EnumService.getRole(resultSet.getString(UserConstant.ROLE));
        boolean blocked = resultSet.getBoolean(UserConstant.BLOCKED);

        return new User(id, name, lastName, email, login, password, role, blocked);
    }


}
