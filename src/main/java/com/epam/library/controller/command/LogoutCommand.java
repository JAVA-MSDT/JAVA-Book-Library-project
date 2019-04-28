package com.epam.library.controller.command;

import com.epam.library.util.constant.UserConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(false);
        if(session != null){
            session.removeAttribute(UserConstant.USER_ATTRIBUTE);
            session.invalidate();
        }

        return PageLocation.MAIN_PAGE;
    }
}
