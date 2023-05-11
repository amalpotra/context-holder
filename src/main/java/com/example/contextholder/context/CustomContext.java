package com.example.contextholder.context;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

/**
 * Represents Authorization header and Tenant properties as an
 * example which can be used to interact with external services.
 * Its lifecycle is bound to the current web request i.e., RequestScope
 *
 * @see RequestScope
 */
@Component
@RequestScope
public class CustomContext {
    private String authToken;

    private Object tenantProps;

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authHeader) {
        this.authToken = authHeader;
    }

    public Object getTenantProps() {
        return tenantProps;
    }

    public void setTenantProps(Object tenantProps) {
        this.tenantProps = tenantProps;
    }
}
