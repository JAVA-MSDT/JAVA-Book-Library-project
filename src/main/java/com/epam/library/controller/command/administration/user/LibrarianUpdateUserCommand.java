package com.epam.library.controller.command.administration.user;

import com.epam.library.controller.builder.UserBuilderFromRequest;
import com.epam.library.controller.command.Command;
import com.epam.library.controller.command.PageLocation;
import com.epam.library.entity.User;
import com.epam.library.model.service.ServiceException;
import com.epam.library.model.service.UserService;
import com.epam.library.util.constant.DiffConstant;
import com.epam.library.util.constant.UserConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LibrarianUpdateUserCommand implements Command {
    private UserService userService;
    private UserBuilderFromRequest builderFromRequest = new UserBuilderFromRequest();

    public LibrarianUpdateUserCommand(UserService userService) {
        this.userService = userService;
    }

    /**
     * @param response to jsp
     * @param request  from jsp
     * @return page depends on if the userId is null that is mean that the request is coming from add user
     * page so we will save the user to the database.
     * if the usrId is not null that is mean that the request coming from editing user page so we will
     * update the existing user
     * @throws ServiceException is something  wrong happens during user update or save
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String page = null;
        HttpSession session = request.getSession();

        String userId = request.getParameter(UserConstant.ID);

        if (userId != null && !userId.isEmpty()) {
            Optional<User> optionalUser = userService.getById(Long.valueOf(userId));
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                User updateUser = builderFromRequest.buildUserForUpdate(request, user);
                userService.update(updateUser);
                request.setAttribute(DiffConstant.SUCCESS_INFO_UPDATE, DiffConstant.READ_FROM_PROPERTIES);

                page = PageLocation.ADMINISTRATION_EDIT_USER;
            }
        } else {
            if (session.getAttribute(DiffConstant.ITEM_INSERTED) == null) { // to prevent double submit
                User user = builderFromRequest.buildUserForInserting(request);
                userService.save(user);
                request.setAttribute(DiffConstant.INSERT_SUCCESS, DiffConstant.READ_FROM_PROPERTIES);
                session.setAttribute(DiffConstant.ITEM_INSERTED, DiffConstant.INSERTED);
                page = PageLocation.ADMINISTRATION_EDIT_USER;
            }else {
                request.setAttribute(DiffConstant.DOUBLE_SUBMIT_ATTEMPT, DiffConstant.READ_FROM_PROPERTIES);
                page = PageLocation.ADMINISTRATION_EDIT_USER;
            }
        }
        return page;
    }

}
