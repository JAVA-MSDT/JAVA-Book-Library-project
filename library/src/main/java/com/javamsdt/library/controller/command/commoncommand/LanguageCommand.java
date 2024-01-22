package com.javamsdt.library.controller.command.commoncommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javamsdt.library.controller.command.Command;
import com.javamsdt.library.controller.command.CommandResult;
import com.javamsdt.library.model.service.ServiceException;
import com.javamsdt.library.util.constant.PageLocation;

public class LanguageCommand implements Command {
    private final static String LANGUAGE_PARAMETER = "language";
    private final static String LANGUAGE_ATTRIBUTE = "language";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        String local = request.getParameter(LANGUAGE_PARAMETER);
        session.setAttribute(LANGUAGE_ATTRIBUTE, local);
        return new CommandResult(PageLocation.MAIN_PAGE);
    }
}
