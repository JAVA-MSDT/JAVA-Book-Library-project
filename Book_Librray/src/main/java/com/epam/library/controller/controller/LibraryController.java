package com.epam.library.controller.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.library.controller.command.Command;
import com.epam.library.controller.command.CommandFactory;
import com.epam.library.controller.command.CommandResult;
import com.epam.library.model.db.ConnectionPool;
import com.epam.library.model.service.ServiceException;
import com.epam.library.util.constant.PageLocation;
import com.epam.library.util.constant.RedirectTo;

@WebServlet("/controller")
public class LibraryController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5032260115126926225L;
	private final static String COMMAND_NAME = "command";
	private final static Logger logger = LogManager.getLogger();

	@Override
	public void init() throws ServletException {
		System.out.println("Library Started>>>> ");
	}

	@Override
	protected void doGet(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse)
			throws ServletException, IOException {
		processRequest(httpServletRequest, httpServletResponse);
	}

	@Override
	protected void doPost(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse)
			throws ServletException, IOException {
		processRequest(httpServletRequest, httpServletResponse);
	}

	private void processRequest(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {

		String command = request.getParameter(COMMAND_NAME);

		try (CommandFactory factory = new CommandFactory()) {
			Command action = factory.create(command);
			CommandResult commandResult = action.execute(request, response);
			dispatch(request, response, commandResult);
		} catch (ServiceException e) {
			logger.error("Exception in Library Controller", e);
			response.sendRedirect(PageLocation.ERROR_PAGE);
		}

	}

	private void dispatch(final HttpServletRequest request, final HttpServletResponse response,
			final CommandResult commandResult) throws ServletException, IOException {
		String page = commandResult.getPage();
		switch (commandResult.getCommandAction()) {
		case FORWARD:
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
			dispatcher.forward(request, response);
			break;
		case REDIRECT:
			response.sendRedirect(page);
			break;
		default:
			response.sendRedirect(RedirectTo.LOGIN_PAGE);
		}
	}

	@Override
	public void destroy() {
		ConnectionPool.getInstance().closePool();
	}
}
