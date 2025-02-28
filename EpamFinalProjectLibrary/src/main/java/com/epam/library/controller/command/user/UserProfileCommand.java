package com.epam.library.controller.command.user;

import com.epam.library.controller.command.Command;
import com.epam.library.controller.command.PageLocation;
import com.epam.library.model.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserProfileCommand implements Command {

    /**
     * @param request  from the jsp
     * @param response to the jsp
     * @return user profile page
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        return PageLocation.USER_PROFILE;
    }
}
