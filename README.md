# Store Custom Data in Spring Request Context

When developing a web application with Spring MVC, we often want to make some data available throughout the current request, like authentication information, request identifier, etc.
These data are injected into a request-scoped context, and destroyed after the request ends.
There are several ways to achieve that, and this example application demonstrate some of the ways to achieve this.

### Using Spring's RequestContextHolder to store this for us
By definition, Spring's `RequestContextHolder` is a holder for `RequestAttributes` that uses a `ThreadLocal` to store these attributes.
This means that the data stored in the `RequestContextHolder` is only available in the current thread.
This is the default implementation of the `RequestContextHolder`, and it is used by Spring internally to store the current request attributes.
We can use this to store our custom data as well.

### Using a custom ContextHolder based on ThreadLocal
This goes one step further than the previous approach.
We can create our own `ContextHolder` that uses a `ThreadLocal` to store our custom data, in other words, our own implementation similar to what of `RequestContextHolder`.
This way, we can store our custom data in the `ContextHolder` and access it from anywhere in the current thread.
A custom implementation like this ease the access to the data, and also makes the code more readable and easier to test.
---

#### Why ThreadLocal?
The `ThreadLocal` class in Java enables us to create variables that can only be read and written by the same thread.
Each servlet request is handled in a separate thread, so we can use a thread-local object to hold the request-scoped context.
Beware the thread that processes your request is borrowed from a thread pool, and you don’t want your previous request info leaking into the next,
so it is important to clean up the thread-local object after the request is processed.
