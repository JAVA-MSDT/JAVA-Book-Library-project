package com.epam.library.controller.command;

import com.epam.library.entity.User;
import com.epam.library.model.service.ServiceException;
import com.epam.library.model.service.UserService;
import com.epam.library.util.constant.DiffConstant;
import com.epam.library.util.constant.PageLocation;
import com.epam.library.util.constant.entityconstant.BookConstant;
import com.epam.library.util.constant.entityconstant.UserConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LoginCommand implements Command {

    private UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String page;
        HttpSession session = request.getSession();
        String login = request.getParameter(UserConstant.LOGIN);
        String password = request.getParameter(UserConstant.PASSWORD);
        Optional<User> optionalUser = userService.findByLoginPassword(login, password);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            session.setAttribute(UserConstant.USER_ATTRIBUTE, user);
            if (!user.isBlocked()) {
                page = PageLocation.PROFILE;
            }else {
                request.setAttribute(UserConstant.BLOCK_MESSAGE, DiffConstant.READ_FROM_PROPERTIES);
                page = PageLocation.LOGIN_PAGE;
            }
        } else {
            request.setAttribute(UserConstant.INVALID_LOGIN, DiffConstant.READ_FROM_PROPERTIES);
            page = PageLocation.LOGIN_PAGE;

        }
        return page;
    }


}
