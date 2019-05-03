package com.epam.library.controller.command.librarian.user;

import com.epam.library.controller.command.Command;
import com.epam.library.entity.User;
import com.epam.library.controller.command.PageLocation;
import com.epam.library.util.constant.UserConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LibrarianAddUserCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = null;
        User user = (User) request.getSession(false).getAttribute(UserConstant.USER_ATTRIBUTE);
        if(user != null){
            page = PageLocation.LIBRARIAN_ADD_READER;
        }
        return page;
    }
}
