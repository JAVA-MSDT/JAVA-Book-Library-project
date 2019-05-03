package com.epam.library.controller.filter;

import com.epam.library.controller.command.PageLocation;
import com.epam.library.entity.User;
import com.epam.library.entity.enumeration.Role;
import com.epam.library.util.constant.UserConstant;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter("/controller")
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(UserConstant.USER_ATTRIBUTE);
        String command = request.getParameter("command");

        if (!commonCommand(command)) {
            if (user.getRole() == Role.LIBRARIAN && librarianCommand(command)) {

                filterChain.doFilter(servletRequest, servletResponse);
            } else if(user.getRole() == Role.READER && readerCommand(command)) {

                filterChain.doFilter(servletRequest, servletResponse);
            } else{
                response.sendRedirect(PageLocation.LOGIN_PAGE);

            }
        }else {
       
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }

    private boolean librarianCommand(String command) {
        List<String> commandList = new ArrayList<>();
        commandList.add("librarian-profile");

        commandList.add("librarian-book-store");
        commandList.add("librarian-edit-book");
        commandList.add("librarian-add-book");
        commandList.add("librarian-update-book");

        commandList.add("librarian-order-list");
        commandList.add("librarian-edit-order");
        commandList.add("librarian-add-order");
        commandList.add("librarian-update-order");

        commandList.add("librarian-display-readers");
        commandList.add("librarian-edit-reader");
        commandList.add("librarian-add-reader");
        commandList.add("librarian-update-reader");

        return commandList.contains(command);
    }

    private boolean readerCommand(String command) {
        List<String> commandList = new ArrayList<>();
        commandList.add("reader-profile");
        commandList.add("reader-book");
        commandList.add("confirm-order");
        commandList.add("reader-order");
        return commandList.contains(command);
    }

    private boolean commonCommand(String command) {
        List<String> commandList = new ArrayList<>();
        commandList.add("Login");
        commandList.add("logout");
        commandList.add("display-book");
        commandList.add("view-book");
        commandList.add("order-book");
        return commandList.contains(command);
    }
}
