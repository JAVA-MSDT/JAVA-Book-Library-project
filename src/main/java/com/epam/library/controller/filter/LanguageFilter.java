package com.epam.library.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
//@WebFilter(filterName = "SessionLocalFilter", urlPatterns = {"/*"})
public class LanguageFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if(request.getParameter("sessionLocal") != null){
            System.out.println("SessionLocal is: " + request.getParameter("sessionLocal"));
            request.getSession().setAttribute("language", request.getParameter("sessionLocal"));
        }
        System.out.println("sessionLocal is Null");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
