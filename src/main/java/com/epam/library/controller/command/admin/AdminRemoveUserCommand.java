package com.epam.library.controller.command.admin;

import com.epam.library.controller.command.Command;
import com.epam.library.controller.command.CommandResult;
import com.epam.library.entity.User;
import com.epam.library.model.service.ServiceException;
import com.epam.library.model.service.UserService;
import com.epam.library.util.constant.Operation;
import com.epam.library.util.constant.RedirectTo;
import com.epam.library.util.constant.entityconstant.UserConstant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
            userService.removeById(Long.valueOf(userId));
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
