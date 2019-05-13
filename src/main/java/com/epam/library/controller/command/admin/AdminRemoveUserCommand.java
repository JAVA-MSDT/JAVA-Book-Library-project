package com.epam.library.controller.command.admin;

import com.epam.library.controller.command.Command;
import com.epam.library.controller.command.PageLocation;
import com.epam.library.entity.User;
import com.epam.library.model.service.ServiceException;
import com.epam.library.model.service.UserService;
import com.epam.library.util.constant.DiffConstant;
import com.epam.library.util.constant.UserConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AdminRemoveUserCommand implements Command {
    private UserService userService;

    public AdminRemoveUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String userId = request.getParameter(UserConstant.ID);
        List<User> users = userService.getAll();
        try {
            userService.removeById(Long.valueOf(userId));
            request.setAttribute(DiffConstant.REMOVE_DONE, DiffConstant.READ_FROM_PROPERTIES);
        } catch (ServiceException e) {
            request.setAttribute(DiffConstant.REMOVE_FAIL, DiffConstant.READ_FROM_PROPERTIES);
        }

        request.setAttribute(UserConstant.USER_LIST, users);
        return PageLocation.ADMINISTRATION_USER_LIST;
    }
}
