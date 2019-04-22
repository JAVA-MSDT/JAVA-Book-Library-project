package com.epam.library.controller.filter;

import com.epam.library.util.PageLocation;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession(false);
        if(session.getAttribute("") == null){
            servletRequest.getServletContext().getRequestDispatcher(request.getContextPath() + PageLocation.LOGIN_PAGE);
        }

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
