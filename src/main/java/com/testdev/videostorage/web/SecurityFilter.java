package com.testdev.videostorage.web;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author oleh.krupenia.
 */
@WebFilter(filterName="securityFilter", urlPatterns = {"/*"})
public class SecurityFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Inside filter.");
        // TODO: if there is no user in the session, redirect to the login page
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
