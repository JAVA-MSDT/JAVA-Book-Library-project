package com.epam.library.controller.command.librarian;


import com.epam.library.controller.command.Command;
import com.epam.library.entity.User;
import com.epam.library.model.service.ServiceException;
import com.epam.library.model.service.ServiceFactory;
import com.epam.library.model.service.UserService;
import com.epam.library.util.PageLocation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class LibrarianEditUser implements Command {

    UserService userService = ServiceFactory.getInstance().getUserService();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = null;
        User user = (User) request.getSession(false).getAttribute("reader");
        if(user != null){
            try {
            String readerId = request.getParameter("id");
                Optional<User> optionalUser = userService.findById(Long.valueOf(readerId));

                optionalUser.ifPresent(value -> request.setAttribute("readerEdit", value));

                page = PageLocation.LIBRARIAN_EDIT_READER;
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }else {
            page = PageLocation.LIBRARIAN_PAGE;
        }
        return page;
    }

}
