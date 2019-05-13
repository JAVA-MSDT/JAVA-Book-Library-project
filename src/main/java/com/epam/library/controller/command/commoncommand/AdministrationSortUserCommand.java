package com.epam.library.controller.command.commoncommand;

import com.epam.library.controller.command.Command;
import com.epam.library.controller.command.PageLocation;
import com.epam.library.entity.User;
import com.epam.library.model.service.ServiceException;
import com.epam.library.model.service.UserService;
import com.epam.library.util.constant.UserConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AdministrationSortUserCommand implements Command {
    private static final String SORT_CRITERIA = "type";
    private UserService userService;

    public AdministrationSortUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String sortCriteria = request.getParameter(SORT_CRITERIA);

        List<User> userList = null;

        if (sortCriteria.equalsIgnoreCase(UserConstant.NAME)) {
            userList = userService.sortUsersByName();
        } else if (sortCriteria.equalsIgnoreCase(UserConstant.EMAIL)) {
            userList = userService.sortUsersByEmail();
        }
        request.setAttribute(UserConstant.USER_LIST, userList);

        return PageLocation.ADMINISTRATION_USER_LIST;
    }
}
