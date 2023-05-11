package com.example.contextholder.filter;

import com.example.contextholder.context.CustomContext;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.filter.RequestContextFilter;

import java.io.IOException;

/**
 * Filter to set CustomContext in Spring's RequestContextHolder
 * before each request is processed.
 *
 * @see RequestContextHolder
 * @see CustomContext
 */
public class ContextFilter extends RequestContextFilter {
    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        ServletRequestAttributes attributes = new ServletRequestAttributes(request, response);
        CustomContext customContext = new CustomContext();

        customContext.setAuthToken(request.getHeader("Authorization"));
        customContext.setTenantProps("props");

        attributes.setAttribute("context", customContext, RequestAttributes.SCOPE_REQUEST);
        try {
            RequestContextHolder.setRequestAttributes(attributes);
            filterChain.doFilter(request, response);
        } finally {
            RequestContextHolder.resetRequestAttributes();
        }
    }
}
