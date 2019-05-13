package com.epam.library.controller.command.admin;

import com.epam.library.controller.command.Command;
import com.epam.library.controller.command.PageLocation;
import com.epam.library.model.service.ServiceException;
import com.epam.library.model.service.UserService;
import com.epam.library.util.constant.DiffConstant;
import com.epam.library.util.constant.UserConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminUpdateRoleCommand implements Command {
    private UserService userService;

    public AdminUpdateRoleCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String userId = request.getParameter(UserConstant.ID);
        String role = request.getParameter(UserConstant.ROLE);
        userService.updateRole(Long.valueOf(userId), role);
        request.setAttribute(DiffConstant.SUCCESS_INFO_UPDATE, DiffConstant.READ_FROM_PROPERTIES);

        return PageLocation.ADMIN_CHANGE_ROLE;
    }
}
