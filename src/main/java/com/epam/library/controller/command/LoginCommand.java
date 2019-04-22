package com.epam.library.controller.command;

import com.epam.library.entity.User;
import com.epam.library.model.service.ServiceException;
import com.epam.library.model.service.ServiceFactory;
import com.epam.library.model.service.UserService;
import com.epam.library.util.PageLocation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LoginCommand implements Command {
    private final static String PARAM_NAME_LOGIN = "login";
    private final static String PARAM_NAME_PASS = "password";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = null;
        UserService userService = ServiceFactory.getInstance().getUserService();
        HttpSession session = request.getSession();
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASS);

        try {
            Optional<User> optionalUser = userService.findByLoginPassword(login, password);
            if (optionalUser.isPresent()) {
                session.setAttribute("reader", optionalUser.get());
                request.setAttribute("reader", optionalUser.get());
                page = defineReaderPage(optionalUser.get());
            } else {
                page = PageLocation.LOGIN_PAGE;
                request.setAttribute("register", "You have no account");

            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return page;
    }

    private String defineReaderPage(User user){
        String readerRole = user.getRole().name();
        String readerPage = null;
        switch (readerRole) {
            case "ADMIN":
                readerPage = PageLocation.ADMIN_PAGE;
                break;
            case "LIBRARIAN":
                readerPage = PageLocation.LIBRARIAN_PAGE;
                break;
            case "READER":
                readerPage = PageLocation.READER_PAGE;
                break;
        }
        return readerPage;
    }

}
