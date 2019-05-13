package com.epam.library.controller.command.administration.user;

import com.epam.library.controller.command.Command;
import com.epam.library.controller.command.PageLocation;
import com.epam.library.entity.User;
import com.epam.library.entity.enumeration.Role;
import com.epam.library.model.service.ServiceException;
import com.epam.library.model.service.UserService;
import com.epam.library.util.constant.UserConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class LibrarianUserListCommand implements Command {
    private UserService userService;

    public LibrarianUserListCommand(UserService userService) {
        this.userService = userService;
    }

    /**
     * @param request  from the jsp
     * @param response to the jsp
     * @return page which holds the information about the users to display them on the page, for the
     * administration to control them, adding or editing
     * @throws ServiceException if something wrong during the connection with database
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        User user = (User) request.getSession(false).getAttribute(UserConstant.USER_ATTRIBUTE);

        List<User> users = null;
        if (user.getRole().equals(Role.ADMIN)) {
            users = userService.getAll();
        } else if (user.getRole().equals(Role.LIBRARIAN)) {
            users = userService.findAllWhereRoleReader();
        }

        request.setAttribute(UserConstant.USER_LIST, users);
        return PageLocation.ADMINISTRATION_USER_LIST;
    }
}
