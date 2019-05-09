package com.epam.library.controller.command.librarian.user;


import com.epam.library.controller.command.Command;
import com.epam.library.controller.command.PageLocation;
import com.epam.library.entity.User;
import com.epam.library.model.service.ServiceException;
import com.epam.library.model.service.UserService;
import com.epam.library.util.constant.UserConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class LibrarianEditUserCommand implements Command {

    UserService userService;

    public LibrarianEditUserCommand(UserService userService) {
        this.userService = userService;
    }

    /**
     * @param request  from the jsp
     * @param response to the jsp
     * @return page which holds a form that has the information about a specific user to be edited
     * @throws ServiceException is something wrong during the connection with database
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String page;
        String userId = request.getParameter(UserConstant.ID);
        if(userId != null){
            Optional<User> optionalUser = userService.getById(Long.valueOf(userId));
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                request.setAttribute(UserConstant.EDIT_USER, user);
                page = PageLocation.ADMINISTRATION_EDIT_USER;
            } else {
                page = PageLocation.PROFILE;
            }
        }else {
            page = PageLocation.ADMINISTRATION_EDIT_USER;
        }

        return page;
    }
}
