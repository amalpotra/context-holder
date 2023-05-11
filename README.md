# Store Custom Data in Spring Request Context

When developing a web application with Spring MVC, we often want to make some data available throughout the current request, like authentication information, request identifier, etc. These data are injected into a request-scoped context, and destroyed after the request ends. There are several ways to achieve that, and this example application demonstrate some of the ways to achieve this.

1. Using Spring's RequestContextHolder to store this for us.
2. Using a custom ContextHolder based on ThreadLocal.
