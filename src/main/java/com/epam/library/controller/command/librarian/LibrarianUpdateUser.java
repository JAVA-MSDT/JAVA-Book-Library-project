package com.epam.library.controller.command.librarian;

import com.epam.library.controller.command.Command;
import com.epam.library.entity.User;
import com.epam.library.model.builder.UserBuilder;
import com.epam.library.model.service.ServiceException;
import com.epam.library.model.service.ServiceFactory;
import com.epam.library.model.service.UserService;
import com.epam.library.util.PageLocation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LibrarianUpdateUser implements Command {
   private UserService userService = ServiceFactory.getInstance().getUserService();
   private UserBuilder userBuilder = new UserBuilder();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = null;
        User user = (User) request.getSession(false).getAttribute("reader");
        HttpSession session = request.getSession();
        if(user != null){
            User updateUser = userBuilder.userFromForm(request);
            try {
                userService.librarianUpdateUser(updateUser);
                session.setAttribute("reader", user);
                page = PageLocation.LIBRARIAN_DISPLAY_READERS;
            } catch (ServiceException e) {
                e.printStackTrace();
            }

        }else {
            page = PageLocation.LIBRARIAN_PAGE;
        }
        return page;
    }

}
