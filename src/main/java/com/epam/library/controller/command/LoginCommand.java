package com.epam.library.controller.command;

import com.epam.library.entity.User;
import com.epam.library.model.service.ServiceException;
import com.epam.library.model.service.UserService;
import com.epam.library.util.constant.DiffConstant;
import com.epam.library.util.constant.UserConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LoginCommand implements Command {

    private UserService userService;
    public LoginCommand(UserService userService){
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String page;
        HttpSession session = request.getSession();
        String login = request.getParameter(UserConstant.LOGIN);
        String password = request.getParameter(UserConstant.PASSWORD);

            Optional<User> optionalUser = userService.findByLoginPassword(login, password);
            if (optionalUser.isPresent()) {
                session.setAttribute(UserConstant.USER_ATTRIBUTE, optionalUser.get());
                request.setAttribute(UserConstant.USER_ATTRIBUTE, optionalUser.get());
                page = defineReaderPage(optionalUser.get(), request);
            } else {
                request.setAttribute(UserConstant.INVALID_LOGIN, DiffConstant.READ_FROM_PROPERTIES);
                page = PageLocation.LOGIN_PAGE;

            }
        return page;
    }


    /**
     *
     * @param user to define his role also to check if it is blocked or not
     * @param request to set the attribute in order to read it later in the jsp page
     * @return user page if it is not block and according to his role.
     */
    private String defineReaderPage(User user, HttpServletRequest request){
        String readerPage = null;

        if(!user.isBlocked()) {
            String readerRole = user.getRole().name();
            switch (readerRole) {
                case "ADMIN":
                    readerPage = PageLocation.ADMIN_PAGE;
                    break;
                case "LIBRARIAN":
                    readerPage = PageLocation.LIBRARIAN_PROFILE;
                    break;
                case "READER":
                    readerPage = PageLocation.READER_PROFILE;
                    break;
            }
        }else{
            readerPage = PageLocation.LOGIN_PAGE;
            request.setAttribute(UserConstant.BLOCK_MESSAGE, DiffConstant.READ_FROM_PROPERTIES);
        }
        return readerPage;
    }


}
