GuiceJerseyJettyShiroExample
============================

Simple example using the Guice-Jersey-Jetty-Shiro stack.

This sample application demonstrates the problem with using Form based authentication with Shiro, Guice and Jetty.

The problem is that Shiro needs login.jsp to be made available through Guice, and then added to Shiro's filter chain. After doing so, however, Jetty can't find login.jsp.

When login.jsp is excluded from the Guice filter, Jetty can find the jsp, but then it's not available to Shiro so the authentication doesn't work.

So, either login.jsp can be made available, or the authentication will work - but not both at the same time.

To replicate, run Bootstrap.class and navigate a web browser to http://localhost:8081/login.jsp.

In Bootstrap, commenting or uncommenting the following line will either enable login.jsp or authentication:
webAppContext.addFilter(GuiceFilter.class, "/login.jsp", null);
