package com.example.contextholder.filter;

import com.example.contextholder.context.CustomContext;
import com.example.contextholder.context.CustomContextHolder;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * OncePerRequestFilter responsible for populating {@link CustomContext}
 * in {@link CustomContextHolder}.
 *
 * @see OncePerRequestFilter
 */
public class ContextFilter extends OncePerRequestFilter {
    private final CustomContextHolder customContextHolder;

    public ContextFilter(CustomContextHolder customContextHolder) {
        this.customContextHolder = customContextHolder;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getRequestURI().matches(".*/swagger-ui.html");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        CustomContext customContext = new CustomContext();
        customContext.setAuthToken(request.getHeader("Authorization"));

        // Possibly a call to a service say tenancy-config, to get tenant properties before this.
        customContext.setTenantProps("props");

        customContextHolder.setContext(customContext);

        try {
            filterChain.doFilter(request, response);
        } finally {
            customContextHolder.removeContext();
        }
    }
}
