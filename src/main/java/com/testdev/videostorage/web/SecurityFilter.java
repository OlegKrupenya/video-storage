package com.testdev.videostorage.web;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author oleh.krupenia.
 */
//@WebFilter(filterName = "securityFilter", urlPatterns = {"/*"})
//public class SecurityFilter implements Filter {
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
////        System.out.println("Inside filter.");
////        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
////        if (httpServletRequest.getSession(false) != null && httpServletRequest.getSession().getAttribute("user") != null
////                || httpServletRequest.getRequestURI().contains("login")) {
////            chain.doFilter(request, response);
////        } else {
////            ((HttpServletResponse) response).sendRedirect("/login.html");
////        }
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}
