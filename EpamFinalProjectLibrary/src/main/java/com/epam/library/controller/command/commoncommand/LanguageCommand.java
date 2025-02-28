package com.epam.library.controller.command.commoncommand;

import com.epam.library.controller.command.Command;
import com.epam.library.controller.command.PageLocation;
import com.epam.library.model.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LanguageCommand implements Command {
    private final static String LANGUAGE_PARAMETER = "language";
    private final static String LANGUAGE_ATTRIBUTE = "language";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        String local = request.getParameter(LANGUAGE_PARAMETER);
        session.setAttribute(LANGUAGE_ATTRIBUTE, local);
        System.out.println("Session is: " + session.getAttribute(LANGUAGE_ATTRIBUTE));
        System.out.println("Local is: " + local);
        return PageLocation.MAIN_PAGE;
    }
}
