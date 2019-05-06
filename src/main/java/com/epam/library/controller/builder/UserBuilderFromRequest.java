package com.epam.library.controller.builder;

import com.epam.library.entity.User;
import com.epam.library.entity.enumeration.Role;
import com.epam.library.util.constant.UserConstant;

import javax.servlet.http.HttpServletRequest;

public class UserBuilderFromRequest {

    /**
     * @param request to extract from it the user data
     * @param role    to get the use Role
     * @return new user after coping the proper data from the request and the user object.
     */
    public User buildUserToLibrarianUpdate(HttpServletRequest request, Role role) {
        String id = request.getParameter(UserConstant.ID);
        String blocked = request.getParameter(UserConstant.BLOCKED);
        boolean blockStatus = getBooleanValue(blocked);

        User user1 = buildToAddUser(request);

        user1.setId(Long.valueOf(id));
        user1.setBlocked(blockStatus);
        user1.setRole(role);

        return user1;
    }

    /**
     * @param request to extract from it the user data
     * @return the needed fields which has no default value in the sql table to use it
     * for saving user in the database
     */
    public User buildToAddUser(HttpServletRequest request) {
        String name = request.getParameter(UserConstant.NAME);
        String lastName = request.getParameter(UserConstant.LAST_NAME);
        String email = request.getParameter(UserConstant.EMAIL);
        String login = request.getParameter(UserConstant.LOGIN);
        String password = request.getParameter(UserConstant.PASSWORD);
        return new User(name, lastName, email, login, password);
    }

    /**
     * @param booleanHolder string with a value true or false
     * @return boolean value depends on the value of the booleanHolder string
     */
    private boolean getBooleanValue(String booleanHolder) {
        return booleanHolder.equalsIgnoreCase("true");
    }
}
