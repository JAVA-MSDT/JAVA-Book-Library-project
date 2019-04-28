package com.epam.library.controller.command;

import com.epam.library.model.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LanguageCommand implements Command {
    private final static String LANGUAGE_PARAMETER = "language";
    private final static String LANGUAGE_ATTRIBUTE = "language";
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession(true);
        String local = request.getParameter(LANGUAGE_PARAMETER);
        session.setAttribute(LANGUAGE_ATTRIBUTE, local);

        return PageLocation.MAIN_PAGE;
    }
}
