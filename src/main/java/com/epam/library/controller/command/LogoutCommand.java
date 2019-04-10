package com.epam.library.controller.command;

import com.epam.library.util.PageLocation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = PageLocation.LOGIN_PAGE;
        request.getSession().invalidate();

        return page;
    }
}
