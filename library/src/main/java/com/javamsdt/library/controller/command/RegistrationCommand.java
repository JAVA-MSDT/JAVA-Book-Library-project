package com.javamsdt.library.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.javamsdt.library.controller.builder.UserBuilderFromRequest;
import com.javamsdt.library.entity.User;
import com.javamsdt.library.model.service.ServiceException;
import com.javamsdt.library.model.service.UserService;
import com.javamsdt.library.util.constant.Operation;
import com.javamsdt.library.util.constant.PageLocation;
import com.javamsdt.library.util.constant.RedirectTo;
import com.javamsdt.library.util.validate.entityvalidate.UserValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class RegistrationCommand implements Command {
    private final static Logger logger = LogManager.getLogger();
    private UserService userService;
    private UserBuilderFromRequest builderFromRequest = new UserBuilderFromRequest();

    public RegistrationCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String operation = null;
        CommandResult commandResult = new CommandResult();
        List<String> userValidation = UserValidator.validateUserParameter(request);
        if (userValidation.size() == 0) {
            User user = builderFromRequest.buildUserForInserting(request);
            try {
                userService.save(user);
                operation = Operation.INSERTED;
            } catch (ServiceException e) {
                operation = Operation.INSERT_FAIL;
                logger.error(e);

            } finally {
                commandResult.redirect(PageLocation.LOGIN_PAGE);
            }
        } else {
            request.setAttribute(Operation.VALIDATION_LIST, userValidation);
            commandResult.forward(PageLocation.REGISTRATION_PAGE);
        }
        return commandResult;
    }
}
