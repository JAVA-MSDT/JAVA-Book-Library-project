package com.epam.library.controller.command;


import com.epam.library.model.dao.DaoException;
import com.epam.library.model.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
    String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException;
}
