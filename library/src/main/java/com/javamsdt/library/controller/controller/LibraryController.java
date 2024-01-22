package com.javamsdt.library.controller.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.javamsdt.library.controller.command.Command;
import com.javamsdt.library.controller.command.CommandFactory;
import com.javamsdt.library.controller.command.CommandResult;
import com.javamsdt.library.model.db.ConnectionPool;
import com.javamsdt.library.model.service.ServiceException;
import com.javamsdt.library.util.constant.PageLocation;
import com.javamsdt.library.util.constant.RedirectTo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class LibraryController extends HttpServlet {
    private final static String COMMAND_NAME = "command";
    private final static Logger logger = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        processRequest(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        processRequest(httpServletRequest, httpServletResponse);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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

    private void dispatch(HttpServletRequest request, HttpServletResponse response, CommandResult commandResult) throws ServletException, IOException {
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

    public void destroy() {
        ConnectionPool.getInstance().closePool();
    }
}
