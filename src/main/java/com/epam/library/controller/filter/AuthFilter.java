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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@WebFilter("/controller")
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        User user =(User) session.getAttribute(UserConstant.USER_ATTRIBUTE);
        String command = request.getParameter("command");
        System.out.println("Command is:" + command);
        System.out.println("User is: " + user);
        if(user.getRole() == Role.LIBRARIAN && librarianPage(command)){
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else {
            response.sendRedirect(PageLocation.LOGIN_PAGE);
        }
    }

    @Override
    public void destroy() {

    }
    public boolean librarianPage(String command){
        List<String> commandPage = new ArrayList<>();
        commandPage.add("librarian-profile");
        commandPage.add("librarian-book-store");
        commandPage.add("librarian-order-list");
        commandPage.add("librarian-display-readers");

        return commandPage.contains(command);
    }

    public String adminpage(String command){
        Map<String, String> pages = new HashMap<>();
        pages.put(command, PageLocation.ADMIN_PAGE);
        return pages.getOrDefault(command, null);
    }

    public String readerPage(String command){
        Map<String, String> pages = new HashMap<>();
        pages.put(command, PageLocation.ADMIN_PAGE);
        return pages.getOrDefault(command, null);
    }
}
