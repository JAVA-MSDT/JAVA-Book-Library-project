package com.epam.library.controller.command.librarian.user;

import com.epam.library.controller.command.Command;
import com.epam.library.entity.User;
import com.epam.library.model.service.ServiceException;
import com.epam.library.model.service.ServiceFactory;
import com.epam.library.model.service.UserService;
import com.epam.library.controller.command.PageLocation;
import com.epam.library.util.constant.UserConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class LibrarianDisplayUser implements Command {
    private UserService userService;

    public LibrarianDisplayUser(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String page;
        User user = (User) request.getSession(false).getAttribute(UserConstant.USER_ATTRIBUTE);

            if(user != null){
                List<User> users = userService.findAllWhereRoleReader();
                request.setAttribute(UserConstant.USER_LIST, users);
                page = PageLocation.LIBRARIAN_DISPLAY_READERS;

            }else {
                page = PageLocation.LIBRARIAN_PROFILE;
            }
        return page;
    }
}
