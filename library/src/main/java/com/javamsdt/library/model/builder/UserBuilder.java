package com.javamsdt.library.model.builder;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.javamsdt.library.entity.User;
import com.javamsdt.library.entity.enumeration.Role;
import com.javamsdt.library.util.EnumService;
import com.javamsdt.library.util.constant.entityconstant.UserConstant;
import com.javamsdt.library.util.validate.ArgumentValidator;

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
        Array roleArray = resultSet.getArray(UserConstant.ROLE);
        String[] roleFromArray = (String[]) roleArray.getArray();

        Role role = EnumService.getRole(roleFromArray[0]);
        boolean blocked = resultSet.getBoolean(UserConstant.BLOCKED);

        return new User(id, name, lastName, email, login, password, role, blocked);
    }


}
