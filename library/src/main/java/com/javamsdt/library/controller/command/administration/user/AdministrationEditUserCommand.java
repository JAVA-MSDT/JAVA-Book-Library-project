package com.javamsdt.library.controller.command.administration.user;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javamsdt.library.controller.command.Command;
import com.javamsdt.library.controller.command.CommandResult;
import com.javamsdt.library.entity.User;
import com.javamsdt.library.model.service.ServiceException;
import com.javamsdt.library.model.service.UserService;
import com.javamsdt.library.util.constant.DiffConstant;
import com.javamsdt.library.util.constant.PageLocation;
import com.javamsdt.library.util.constant.entityconstant.UserConstant;

import java.util.Optional;

public class AdministrationEditUserCommand implements Command {

    private UserService userService;

    public AdministrationEditUserCommand(UserService userService) {
        this.userService = userService;
    }

    /**
     * @param request  from the jsp
     * @param response to the jsp
     * @return page which holds a form that has the information about a specific user to be edited
     * @throws ServiceException is something wrong during the connection with database
     */
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String page;
        String userId = request.getParameter(UserConstant.ID);
        if(userId != null){
            Optional<User> optionalUser = userService.getById(Long.parseLong(userId));
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                request.setAttribute(UserConstant.EDIT_USER, user);
                page = PageLocation.ADMINISTRATION_EDIT_USER;
            } else {
                request.setAttribute(UserConstant.USER_NOT_EXIST, DiffConstant.READ_FROM_PROPERTIES);
                page = PageLocation.ADMINISTRATION_USER_LIST;
            }
        }else {
            page = PageLocation.ADMINISTRATION_EDIT_USER;
        }

        return new CommandResult(page);
    }
}
