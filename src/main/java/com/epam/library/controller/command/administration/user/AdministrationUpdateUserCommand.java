package com.epam.library.controller.command.administration.user;

import com.epam.library.controller.builder.UserBuilderFromRequest;
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
import java.util.Optional;

public class AdministrationUpdateUserCommand implements Command {
    private final static Logger logger = LogManager.getLogger();

    private UserService userService;
    private UserBuilderFromRequest builderFromRequest = new UserBuilderFromRequest();

    public AdministrationUpdateUserCommand(UserService userService) {
        this.userService = userService;
    }

    /**
     * @param response to jsp
     * @param request  from jsp
     * @return page depends on if the userId is null that is mean that the request is coming from add user
     * page so we will save the user to the database.
     * if the usrId is not null that is mean that the request coming from editing user page so we will
     * update the existing user
     */
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        String operation = null;
        CommandResult commandResult = new CommandResult();
        String userId = request.getParameter(UserConstant.ID);

        if (userId != null && !userId.isEmpty()) {
            Optional<User> optionalUser = Optional.empty();
            try {
                optionalUser = userService.getById(Long.valueOf(userId));
            } catch (ServiceException e) {
                logger.error(e);
            }
            if (optionalUser.isPresent()) {

                User updateUser = builderFromRequest.buildUserForUpdate(request);
                try {
                    userService.update(updateUser);
                    operation = Operation.UPDATED;
                } catch (ServiceException e) {
                    operation = Operation.UPDATE_FAIL;
                    logger.error(e);
                }
            }
        } else {
            User user = builderFromRequest.buildUserForInserting(request);
            try {
                userService.save(user);
                operation = Operation.INSERTED;
            } catch (ServiceException e) {
                logger.error(e);
                operation = Operation.INSERT_FAIL;

            }
        }

        commandResult.redirect(RedirectTo.ADMINISTRATION_EDIT_USER_PAGE + Operation.OPERATION_STATUS + operation);
        return commandResult;
    }

}
