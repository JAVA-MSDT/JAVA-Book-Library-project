package com.epam.library.controller.command;

import com.epam.library.util.constant.PageLocation;
import com.epam.library.util.constant.entityconstant.UserConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute(UserConstant.USER_ATTRIBUTE);
            session.invalidate();
        }
        CommandResult commandResult = new CommandResult();
        commandResult.redirect(PageLocation.LOGIN_PAGE);
        return commandResult;
    }
}
