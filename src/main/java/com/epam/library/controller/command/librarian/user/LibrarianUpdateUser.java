package com.epam.library.controller.command.librarian.user;

import com.epam.library.controller.command.Command;
import com.epam.library.entity.User;
import com.epam.library.model.builder.UserBuilder;
import com.epam.library.model.service.ServiceException;
import com.epam.library.model.service.UserService;
import com.epam.library.controller.command.PageLocation;
import com.epam.library.util.constant.UserConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class LibrarianUpdateUser implements Command {
   private UserService userService;
   private UserBuilder userBuilder = new UserBuilder();

    public LibrarianUpdateUser(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String page;
        User user = (User) request.getSession(false).getAttribute(UserConstant.USER_ATTRIBUTE);
        if(user != null){
                String userId = request.getParameter(UserConstant.ID);
                page = updateOrSave(userId, request);

        }else {
            page = PageLocation.LIBRARIAN_PROFILE;
        }
        return page;
    }

    /**
     *
     * @param userId to be checked for null value
     * @param request to use for user building
     * @return page depends on if the user is it for editing or for saving
     * @throws ServiceException is something  wrong happens during user update or save
     */
    private String updateOrSave(String userId, HttpServletRequest request) throws ServiceException {

        if(userId != null){
            Optional<User> optionalUser = userService.findById(Long.valueOf(userId));
            User updateUser = userBuilder.buildUserToLibrarianUpdate(request, optionalUser.get());
            userService.updateUser(updateUser);

            return PageLocation.LIBRARIAN_EDIT_READER;
        }else {
            User user = userBuilder.buildToAddUser(request);
            userService.saveUser(user);
            return PageLocation.LIBRARIAN_ADD_READER;
        }
    }

}
