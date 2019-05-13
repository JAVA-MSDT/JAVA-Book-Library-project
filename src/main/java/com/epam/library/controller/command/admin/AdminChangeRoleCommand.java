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
import java.util.Optional;

public class AdminChangeRoleCommand implements Command {

    private UserService userService;

    public AdminChangeRoleCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String page;
        String userId = request.getParameter(UserConstant.ID);
        if(userId != null){
            Optional<User> optionalUser = userService.getById(Long.valueOf(userId));
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                request.setAttribute(UserConstant.EDIT_USER, user);
                page = PageLocation.ADMIN_CHANGE_ROLE;
            } else {
                request.setAttribute(UserConstant.USER_NOT_EXIST, DiffConstant.READ_FROM_PROPERTIES);
                page = PageLocation.ADMINISTRATION_USER_LIST;
            }
        }else {
            request.setAttribute(UserConstant.USER_NOT_EXIST, DiffConstant.READ_FROM_PROPERTIES);
            page = PageLocation.ADMINISTRATION_USER_LIST;
        }

        return page;
    }
}
