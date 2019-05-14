package com.epam.library.controller.filter;

import com.epam.library.controller.command.PageLocation;
import com.epam.library.entity.User;
import com.epam.library.entity.enumeration.Role;
import com.epam.library.util.constant.CommandName;
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
        String command = request.getParameter(CommandName.COMMAND_NAME);

        if (!commonCommand(command)) {
            if (user.getRole() == Role.LIBRARIAN && administrationCommand(command)) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else if (user.getRole() == Role.ADMIN && administrationCommand(command)) {
                filterChain.doFilter(servletRequest, servletResponse);
            }else if (user.getRole() == Role.READER && userCommand(command)) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
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
        commandList.add(CommandName.PROFILE);

        commandList.add(CommandName.ADMINISTRATION_BOOK_STORE);
        commandList.add(CommandName.ADMINISTRATION_EDIT_BOOK);
        commandList.add(CommandName.ADMINISTRATION_UPDATE_BOOK);

        commandList.add(CommandName.ADMINISTRATION_ORDER_LIST);
        commandList.add(CommandName.ADMINISTRATION_EDIT_ORDER);
        commandList.add(CommandName.ADMINISTRATION_UPDATE_ORDER);
        commandList.add(CommandName.ADMINISTRATION_SORT_ORDER);
        commandList.add(CommandName.ADMINISTRATION_SEARCH_ORDER);

        commandList.add(CommandName.ADMINISTRATION_DISPLAY_USER);
        commandList.add(CommandName.ADMINISTRATION_EDIT_USER);
        commandList.add(CommandName.ADMINISTRATION_UPDATE_USER);
        commandList.add(CommandName.ADMINISTRATION_SEARCH_USER);
        commandList.add(CommandName.ADMINISTRATION_SORT_USER);

        // Admin Only
        commandList.add(CommandName.ADMIN_REMOVE_BOOK);
        commandList.add(CommandName.ADMIN_REMOVE_USER);
        commandList.add(CommandName.ADMIN_CHANGE_ROLE);
        commandList.add(CommandName.ADMIN_UPDATE_ROLE);

        return commandList.contains(command);
    }

    /**
     * @param command to be checked if the user authorised to access on of this command or not
     * @return true if the user authorised or false if it is not authorised
     */
    private boolean userCommand(String command) {
        List<String> commandList = new ArrayList<>();
        commandList.add(CommandName.PROFILE);
        commandList.add(CommandName.DISPLAY_BOOK);
        commandList.add(CommandName.CONFIRM_ORDER);
        commandList.add(CommandName.USER_ORDER);
        return commandList.contains(command);
    }

    /**
     * @param command that are common for all the users registered or not
     * @return true if it is for all the users or false.
     */
    private boolean commonCommand(String command) {
        List<String> commandList = new ArrayList<>();
        commandList.add(CommandName.LOGIN);
        commandList.add(CommandName.LOGOUT);
        commandList.add(CommandName.CHANGE_LANGUAGE);
        commandList.add(CommandName.DISPLAY_BOOK);
        commandList.add(CommandName.VIEW_BOOK);
        commandList.add(CommandName.ORDER_BOOK);
        commandList.add(CommandName.SEARCH_BOOK);
        commandList.add(CommandName.SORT_BOOK);
        return commandList.contains(command);
    }
}
