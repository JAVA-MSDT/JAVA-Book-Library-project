package com.epam.library.controller.command.librarian.order;

import com.epam.library.controller.command.Command;
import com.epam.library.controller.command.PageLocation;
import com.epam.library.entity.User;
import com.epam.library.util.constant.UserConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LibrarianAddOrder implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page;
        User user = (User) request.getSession(false).getAttribute(UserConstant.USER_ATTRIBUTE);
        if(user != null){
            page = PageLocation.LIBRARIAN_ADD_ORDER;
        }else {
            page = PageLocation.LIBRARIAN_EDIT_ORDER;
        }
        return page;
    }
}
