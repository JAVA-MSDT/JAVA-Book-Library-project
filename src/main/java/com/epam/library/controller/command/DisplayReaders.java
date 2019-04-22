package com.epam.library.controller.command;

import com.epam.library.entity.User;
import com.epam.library.model.service.ServiceException;
import com.epam.library.model.service.ServiceFactory;
import com.epam.library.model.service.UserService;
import com.epam.library.util.PageLocation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class DisplayReaders implements Command {
    private UserService userService = ServiceFactory.getInstance().getUserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = null;
        User user = (User) request.getSession(false).getAttribute("reader");
        try {
            if(user == null){
                page = PageLocation.LOGIN_PAGE;

            }else {

                List<User> users = userService.getAllUsers();
                request.setAttribute("usersList", users);
                page = PageLocation.LIBRARIAN_DISPLAY_READERS;

            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return page;
    }
}
