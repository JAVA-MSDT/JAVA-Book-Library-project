package com.epam.library.controller.controller;

import com.epam.library.controller.command.Command;
import com.epam.library.controller.command.CommandFactory;
import com.epam.library.controller.command.PageLocation;
import com.epam.library.model.db.ConnectionPool;
import com.epam.library.model.service.ServiceException;

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

        String page = null;
        try (CommandFactory commandFactory = new CommandFactory()) {
            Command action = commandFactory.create(command);
            page = action.execute(request, response);
        } catch (ServiceException e) {
            response.sendRedirect(PageLocation.ERROR_PAGE);
        }

        dispatch(request, response, page);
    }

    private void dispatch(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

    public void destroy(){
        ConnectionPool.getInstance().closePool();
    }
}
