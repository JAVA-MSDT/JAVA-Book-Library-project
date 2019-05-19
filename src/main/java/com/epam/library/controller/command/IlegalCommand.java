package com.epam.library.controller.command;

import com.epam.library.model.service.ServiceException;
import com.epam.library.util.constant.PageLocation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IlegalCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        return new CommandResult(PageLocation.ERROR_PAGE);
    }
}
