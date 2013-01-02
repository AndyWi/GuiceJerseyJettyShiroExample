package eg.guicejerseyjettyshiro;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.webapp.WebAppContext;

import com.google.inject.servlet.GuiceFilter;

import eg.guicejerseyjettyshiro.modules.EgGuiceServletContextListener;

public class Bootstrap {

	public static void main(String[] args) throws Exception {
		Server server = new Server(8081);

		WebAppContext webAppContext = new WebAppContext();
		webAppContext.setContextPath("/");
		webAppContext.setResourceBase("src/main/webapp/");
		webAppContext.setParentLoaderPriority(true);

		webAppContext.addEventListener(new EgGuiceServletContextListener());

		webAppContext.addFilter(GuiceFilter.class, "/api/*", null);

		// **** Shiro needs login.jsp to go through the GuiceFilter,
		// but Jetty can't find the jsp when this happens. Commenting
		// out this line lets Jetty find the jsp, but Shiro can't see it:
		//webAppContext.addFilter(GuiceFilter.class, "/login.jsp", null);

		webAppContext.addServlet(DefaultServlet.class, "/");

		server.setHandler(webAppContext);

		server.start();
		server.join();
	}

}
