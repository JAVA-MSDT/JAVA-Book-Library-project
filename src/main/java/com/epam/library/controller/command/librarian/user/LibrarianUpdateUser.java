package com.epam.library.controller.command.librarian.user;

import com.epam.library.controller.command.Command;
import com.epam.library.entity.User;
import com.epam.library.model.builder.UserBuilder;
import com.epam.library.model.service.ServiceException;
import com.epam.library.model.service.ServiceFactory;
import com.epam.library.model.service.UserService;
import com.epam.library.util.PageLocation;
import com.epam.library.util.constant.UserConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class LibrarianUpdateUser implements Command {
   private UserService userService = ServiceFactory.getInstance().getUserService();
   private UserBuilder userBuilder = new UserBuilder();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = null;
        User user = (User) request.getSession(false).getAttribute(UserConstant.USER_ATTRIBUTE);
        if(user != null){
            request.setAttribute(UserConstant.USER_ATTRIBUTE, user);
            try {
                String userId = request.getParameter(UserConstant.ID);
                page = findPage(userId, request);

            } catch (ServiceException e) {
                e.printStackTrace();
            }

        }else {
            page = PageLocation.LIBRARIAN_PROFILE;
        }
        return page;
    }

    private String findPage(String userId, HttpServletRequest request) throws ServiceException {

        if(userId != null){
            Optional<User> optionalUser = userService.findById(Long.valueOf(userId));
            User updateUser = userBuilder.buildUserToLibrarianUpdate(request, optionalUser.get());
            userService.librarianUpdateUser(updateUser);

            return PageLocation.LIBRARIAN_EDIT_READER;
        }else {
            User user = userBuilder.buildToAddUser(request);
            userService.saveUser(user);
            return PageLocation.LIBRARIAN_ADD_READER;
        }
    }

}
