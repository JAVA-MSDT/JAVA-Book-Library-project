package com.epam.library.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * To prevent the user from backing into his account after logging out, by clearing the cache.
 */
//@WebFilter("/controller")
public class NoCacheFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setHeader("Cache-Control", "no-cache, no-store, must-revlaidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        filterChain.doFilter(servletRequest, response);
    }

    @Override
    public void destroy() {

    }
}
