package com.javamsdt.library.controller.command.administration.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javamsdt.library.controller.command.Command;
import com.javamsdt.library.controller.command.CommandResult;
import com.javamsdt.library.entity.User;
import com.javamsdt.library.entity.enumeration.Role;
import com.javamsdt.library.model.service.ServiceException;
import com.javamsdt.library.model.service.UserService;
import com.javamsdt.library.util.constant.PageLocation;
import com.javamsdt.library.util.constant.entityconstant.UserConstant;

import java.util.List;

public class AdministrationUserListCommand implements Command {
    private UserService userService;

    public AdministrationUserListCommand(UserService userService) {
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
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        User user = (User) request.getSession(false).getAttribute(UserConstant.USER_ATTRIBUTE);

        List<User> users = null;
        if (user.getRole().equals(Role.ADMIN)) {
            users = userService.getAll();
        } else if (user.getRole().equals(Role.LIBRARIAN)) {
            users = userService.findAllWhereRoleReader();
        }
        request.setAttribute(UserConstant.USER_LIST, users);

        return new CommandResult(PageLocation.ADMINISTRATION_USER_LIST);
    }
}
