package com.epam.library.controller.command;

import com.epam.library.model.propertieshandling.ConfigurationManager;
import com.epam.library.model.propertieshandling.MessageManager;
import com.epam.library.util.PageLocation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCommand implements Command {
    private final static String PARAM_NAME_LOGIN = "login";
    private final static String PARAM_NAME_PASS = "password";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page;

        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASS);
        if (LoginLogic.checkUserData(login, password)) {

            request.setAttribute("user", login);
            page = PageLocation.ADMIN_PAGE;
        } else {
            request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginError"));
            page = ConfigurationManager.getProperty("path.page.login");

        }
        return page;
    }
}
