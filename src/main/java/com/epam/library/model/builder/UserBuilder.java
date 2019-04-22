package com.epam.library.model.builder;

import com.epam.library.entity.User;
import com.epam.library.entity.enumeration.Role;
import com.epam.library.util.validate.ArgumentValidator;
import com.epam.library.util.EnumService;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBuilder implements Builder<User> {

    /**
     *
     * @param resultSet which has the info of the user
     * @return user after extracting the date from the data base.
     * @throws SQLException
     */
    @Override
    public User build(ResultSet resultSet) throws SQLException {
        ArgumentValidator.checkForNull(resultSet, "Not allow for null Result set in UserBuilder");

            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            String lastName = resultSet.getString("last_name");
            String email = resultSet.getString("email");
            String login = resultSet.getString("login");
            String password = resultSet.getString("password");
            Role role = EnumService.getRole(resultSet.getString("role"));
            boolean blocked = resultSet.getBoolean("blocked");


        return new User(id, name, lastName, email, login, password, role, blocked);
    }

    public User userFromForm(HttpServletRequest request){
        String id = request.getParameter("librarianReaderId");
        String name = request.getParameter("librarianReaderName");
        String lastName = request.getParameter("librarianReaderLastName");
        String email = request.getParameter("librarianReaderEmail");
        String login = request.getParameter("librarianReaderLogin");
        String password = request.getParameter("librarianReaderPassword");
        String blocked = request.getParameter("librarianReaderBlocked");

        return new User(Long.valueOf(id),name, lastName, email, login, password, Boolean.getBoolean(blocked));
    }
}
