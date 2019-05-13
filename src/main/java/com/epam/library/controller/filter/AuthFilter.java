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

/**
 * Authorised filter to direct the users depends on their role to the right page where they are authorised
 * to use and to go
 */
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
            if (user.getRole() == Role.LIBRARIAN && administrationCommand(command)) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else if (user.getRole() == Role.ADMIN && administrationCommand(command)) {
                filterChain.doFilter(servletRequest, servletResponse);
            }else if (user.getRole() == Role.READER && userCommand(command)) {

                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                System.out.println("Filter Error");
                response.sendRedirect(PageLocation.LOGIN_PAGE);

            }
        } else {

            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }

    /**
     * @param command to be checked if the administration authorised to access it or not
     * @return true if the admin authorised or false if it is not authorised
     */
    private boolean administrationCommand(String command) {
        List<String> commandList = new ArrayList<>();
        commandList.add("profile");

        commandList.add("administration-book-store");
        commandList.add("administration-edit-book");
        commandList.add("administration-update-book");

        commandList.add("administration-order-list");
        commandList.add("administration-edit-order");
        commandList.add("administration-update-order");
        commandList.add("administration-sort-order");
        commandList.add("administration-search-order");

        commandList.add("administration-display-user");
        commandList.add("administration-edit-user");
        commandList.add("administration-update-user");
        commandList.add("administration-search-user");
        commandList.add("administration-sort-user");
        // Admin Only

        commandList.add("admin-remove-book");
        commandList.add("admin-change-role");
        commandList.add("admin-remove-user");
        commandList.add("admin-update-role");

        return commandList.contains(command);
    }

    /**
     * @param command to be checked if the user authorised to access it or not
     * @return true if the user authorised or false if it is not authorised
     */
    private boolean userCommand(String command) {
        List<String> commandList = new ArrayList<>();
        commandList.add("profile");
        commandList.add("user-book");
        commandList.add("confirm-order");
        commandList.add("user-order");
        return commandList.contains(command);
    }

    /**
     * @param command that are common for all the users registered or not
     * @return true if it is for all the users or false.
     */
    private boolean commonCommand(String command) {
        List<String> commandList = new ArrayList<>();
        commandList.add("Login");
        commandList.add("logout");
        commandList.add("display-book");
        commandList.add("view-book");
        commandList.add("order-book");
        commandList.add("change-language");
        commandList.add("search-book");
        commandList.add("sort-book");
        return commandList.contains(command);
    }
}
