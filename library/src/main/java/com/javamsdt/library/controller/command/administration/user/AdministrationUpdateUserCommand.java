package com.javamsdt.library.controller.command.administration.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.javamsdt.library.controller.builder.UserBuilderFromRequest;
import com.javamsdt.library.controller.command.Command;
import com.javamsdt.library.controller.command.CommandResult;
import com.javamsdt.library.entity.User;
import com.javamsdt.library.model.service.ServiceException;
import com.javamsdt.library.model.service.UserService;
import com.javamsdt.library.util.constant.Operation;
import com.javamsdt.library.util.constant.PageLocation;
import com.javamsdt.library.util.constant.RedirectTo;
import com.javamsdt.library.util.constant.entityconstant.UserConstant;
import com.javamsdt.library.util.validate.entityvalidate.UserValidator;

public class AdministrationUpdateUserCommand implements Command {
	private final static Logger logger = LogManager.getLogger();

	private UserService userService;
	private UserBuilderFromRequest builderFromRequest = new UserBuilderFromRequest();

	public AdministrationUpdateUserCommand(final UserService userService) {
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
	public CommandResult execute(final HttpServletRequest request, final HttpServletResponse response) {

		CommandResult commandResult;
		String userId = request.getParameter(UserConstant.ID);

		if ((userId != null) && !userId.isEmpty()) {

			commandResult = updateUser(request);
		} else {

			commandResult = insertUser(request);
		}
		return commandResult;
	}

	/**
	 * In case the user parameter validation fail we will forward the request with a message to the edit
	 * user page,
	 * In case the parameter validation pass we will update the specified user then send redirect to
	 * edit user page.
	 * @param request extract the user parameter for validation then building the user object to update it
	 * @return commandResult
	 */
	private CommandResult updateUser(final HttpServletRequest request) {
		String operation = null;
		CommandResult commandResult = new CommandResult();
		List<String> userValidation = UserValidator.validateUserParameter(request);
		if (userValidation.size() == 0) {
			User updateUser = builderFromRequest.buildUserForUpdate(request);
			try {
				userService.update(updateUser);
				operation = Operation.UPDATED;
			} catch (ServiceException e) {
				operation = Operation.UPDATE_FAIL;
				logger.error(e);
			} finally {
				commandResult.redirect(request.getContextPath() + RedirectTo.ADMINISTRATION_EDIT_USER_PAGE + Operation.OPERATION_STATUS + operation);
			}
		} else {
			request.setAttribute(Operation.VALIDATION_LIST, userValidation);
			commandResult.forward(request.getContextPath() + PageLocation.ADMINISTRATION_EDIT_USER);
		}
		return commandResult;
	}


	/**
	 * In case the user parameter validation fail we will forward the request with a message to the edit
	 * user page,
	 * In case the parameter validation pass we will insert the new user into the database then send redirect to
	 * edit user page.
	 * @param request extract the user parameter for validation then building the book object to update it
	 * @return commandResult
	 */
	private CommandResult insertUser(final HttpServletRequest request) {
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
				commandResult.redirect(request.getContextPath() + RedirectTo.ADMINISTRATION_EDIT_USER_PAGE + Operation.OPERATION_STATUS + operation);
			}
		} else {
			request.setAttribute(Operation.VALIDATION_LIST, userValidation);
			commandResult.forward(request.getContextPath() + PageLocation.ADMINISTRATION_EDIT_USER);
		}
		return commandResult;
	}

}
