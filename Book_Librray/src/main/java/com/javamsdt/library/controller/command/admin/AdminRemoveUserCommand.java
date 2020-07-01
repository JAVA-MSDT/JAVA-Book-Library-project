package com.javamsdt.library.controller.command.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.javamsdt.library.controller.command.Command;
import com.javamsdt.library.controller.command.CommandResult;
import com.javamsdt.library.entity.User;
import com.javamsdt.library.model.service.ServiceException;
import com.javamsdt.library.model.service.UserService;
import com.javamsdt.library.util.constant.Operation;
import com.javamsdt.library.util.constant.RedirectTo;
import com.javamsdt.library.util.constant.entityconstant.UserConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AdminRemoveUserCommand implements Command {
    private final static Logger logger = LogManager.getLogger();
    private UserService userService;

    public AdminRemoveUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String operation;
        CommandResult commandResult = new CommandResult();
        String userId = request.getParameter(UserConstant.ID);
        List<User> users = userService.getAll();
        try {
            userService.removeById(Long.parseLong(userId));
            operation = Operation.REMOVED;
        } catch (ServiceException e) {
            operation = Operation.REMOVE_FAIL;
            logger.error(e);
        }

        request.setAttribute(UserConstant.USER_LIST, users);
        commandResult.redirect(RedirectTo.ADMINISTRATION_USER_LIST_PAGE + Operation.OPERATION_STATUS + operation);
        return commandResult;
    }
}
