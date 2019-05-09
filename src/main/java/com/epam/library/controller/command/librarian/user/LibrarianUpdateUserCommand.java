package com.epam.library.controller.command.librarian.user;

import com.epam.library.controller.builder.UserBuilderFromRequest;
import com.epam.library.controller.command.Command;
import com.epam.library.controller.command.PageLocation;
import com.epam.library.entity.User;
import com.epam.library.entity.enumeration.Role;
import com.epam.library.model.service.ServiceException;
import com.epam.library.model.service.UserService;
import com.epam.library.util.constant.UserConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        String userId = request.getParameter(UserConstant.ID);

        if (userId != null && !userId.isEmpty()) {
            Optional<User> optionalUser = userService.getById(Long.valueOf(userId));
            if (optionalUser.isPresent()) {
                Role role = optionalUser.get().getRole();
                User updateUser = builderFromRequest.buildUserToLibrarianUpdate(request, role);
                userService.update(updateUser);
                page = PageLocation.ADMINISTRATION_EDIT_USER;
            }
        } else {
            User user = builderFromRequest.buildToAddUser(request);
            userService.save(user);
            page = PageLocation.ADMINISTRATION_EDIT_USER;
        }
        return page;
    }

}
