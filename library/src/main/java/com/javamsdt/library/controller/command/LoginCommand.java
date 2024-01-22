package com.javamsdt.library.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javamsdt.library.entity.User;
import com.javamsdt.library.model.service.ServiceException;
import com.javamsdt.library.model.service.UserService;
import com.javamsdt.library.util.constant.DiffConstant;
import com.javamsdt.library.util.constant.PageLocation;
import com.javamsdt.library.util.constant.entityconstant.UserConstant;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

public class LoginCommand implements Command {

    private UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String page;
        CommandResult commandResult = new CommandResult();
        HttpSession session = request.getSession();
        setBuildAndTimeStamp(session);

        String login = request.getParameter(UserConstant.LOGIN);
        String password = request.getParameter(UserConstant.PASSWORD);
        Optional<User> optionalUser = userService.findByLoginPassword(login, password);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            session.setAttribute(UserConstant.USER_ATTRIBUTE, user);
            if (!user.isBlocked()) {
                page = PageLocation.PROFILE;
            }else {
                request.setAttribute(UserConstant.BLOCK_MESSAGE, DiffConstant.READ_FROM_PROPERTIES);
                page = PageLocation.LOGIN_PAGE;
            }
        } else {
            request.setAttribute(UserConstant.INVALID_LOGIN, DiffConstant.READ_FROM_PROPERTIES);
            page = PageLocation.LOGIN_PAGE;

        }
        commandResult.forward(page);
        return commandResult;
    }

    private void setBuildAndTimeStamp(HttpSession session){
        InputStream in = getClass().getClassLoader().getResourceAsStream("buildInfo.properties");
        if (in == null)
            return;

        Properties props = new Properties();
        try {
            props.load(in);
            String version = props.getProperty("build.version");
            String timeStamp = props.getProperty("build.timestamp");

            session.setAttribute("version", version);
            session.setAttribute("timeStamp", timeStamp);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
