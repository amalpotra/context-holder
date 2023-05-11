package com.example.contextholder.context;

import org.springframework.stereotype.Component;

/**
 * Implementation class for CustomContextHolder providing CustomContext.
 * Uses ThreadLocal object to hold request scoped CustomContext.
 *
 * @see CustomContext
 * @see ThreadLocal
 */
@Component
public class CustomContextHolderImpl implements CustomContextHolder {
    private static final ThreadLocal<CustomContext> contextHolder = new ThreadLocal<>();

    public void setContext(CustomContext customContext) {
        contextHolder.set(customContext);
    }

    public CustomContext getContext() {
        return contextHolder.get();
    }

    public void removeContext() {
        contextHolder.remove();
    }
}
