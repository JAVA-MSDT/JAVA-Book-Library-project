package com.epam.library.controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.library.controller.builder.UserBuilderFromRequest;
import com.epam.library.entity.User;
import com.epam.library.model.service.ServiceException;
import com.epam.library.model.service.UserService;
import com.epam.library.util.constant.Operation;
import com.epam.library.util.constant.PageLocation;
import com.epam.library.util.validate.entityvalidate.UserValidator;

public class RegistrationCommand implements Command {
	private final static Logger logger = LogManager.getLogger();
	private UserService userService;
	private UserBuilderFromRequest builderFromRequest = new UserBuilderFromRequest();

	public RegistrationCommand(final UserService userService) {
		this.userService = userService;
	}

	@Override
	public CommandResult execute(final HttpServletRequest request, final HttpServletResponse response)
			throws ServiceException {
		// String operation = null;
		CommandResult commandResult = new CommandResult();
		List<String> userValidation = UserValidator.validateUserParameter(request);
		if (userValidation.size() == 0) {
			User user = builderFromRequest.buildUserForInserting(request);
			try {
				userService.save(user);
				// operation = Operation.INSERTED;
			} catch (ServiceException e) {
				// operation = Operation.INSERT_FAIL;
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
