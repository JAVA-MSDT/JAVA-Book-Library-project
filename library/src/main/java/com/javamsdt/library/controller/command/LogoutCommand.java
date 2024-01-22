package com.javamsdt.library.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javamsdt.library.util.constant.PageLocation;
import com.javamsdt.library.util.constant.entityconstant.UserConstant;

public class LogoutCommand implements Command {

	@Override
	public CommandResult execute(final HttpServletRequest request, final HttpServletResponse response) {

		HttpSession session = request.getSession(false);
		if (session != null) {
			session.removeAttribute(UserConstant.USER_ATTRIBUTE);
			session.invalidate();
		}
		CommandResult commandResult = new CommandResult();
		commandResult.redirect(request.getContextPath() + PageLocation.LOGIN_PAGE);
		return commandResult;
	}
}
