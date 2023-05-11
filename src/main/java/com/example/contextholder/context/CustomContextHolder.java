package com.example.contextholder.context;

/**
 * Request context holder containing CustomContext.
 * Can be used to set, get or remove CustomContext from holder.
 * This is ideally to be done at some filter level.
 * Use cases can be such as setting Authorization headers from request
 * to the context to be able to pass them along with external service interactions
 * or possibly current tenant properties.
 *
 * @see CustomContext
 */
public interface CustomContextHolder {
    void setContext(CustomContext customContext);

    CustomContext getContext();

    void removeContext();
}
