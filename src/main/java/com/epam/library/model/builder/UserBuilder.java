package com.epam.library.model.builder;

import com.epam.library.entity.User;
import com.epam.library.entity.enumeration.Role;
import com.epam.library.util.constant.UserConstant;
import com.epam.library.util.validate.ArgumentValidator;
import com.epam.library.util.EnumService;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;

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


    public User buildUserToLibrarianUpdate(HttpServletRequest request, User user) {
        String id = request.getParameter(UserConstant.ID);
        String blocked = request.getParameter(UserConstant.BLOCKED);
        boolean blockStatus = getBooleanValue(blocked);

        User user1 = buildToAddUser(request);

        user1.setId(Long.valueOf(id));
        user1.setBlocked(blockStatus);
        user1.setRole(user.getRole());

        return user1;
    }

    public User buildToAddUser(HttpServletRequest request) {
        String name = request.getParameter(UserConstant.NAME);
        String lastName = request.getParameter(UserConstant.LAST_NAME);
        String email = request.getParameter(UserConstant.EMAIL);
        String login = request.getParameter(UserConstant.LOGIN);
        String password = request.getParameter(UserConstant.PASSWORD);
        return new User(name, lastName, email, login, password);
    }

    private boolean getBooleanValue(String booleanHolder) {
        return booleanHolder.equalsIgnoreCase("true");
    }
}
