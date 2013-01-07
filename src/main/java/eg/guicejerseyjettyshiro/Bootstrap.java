package eg.guicejerseyjettyshiro;

import org.eclipse.jetty.server.Server;
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

		webAppContext.addFilter(GuiceFilter.class, "/*", null);

		server.setHandler(webAppContext);

		server.start();
		server.join();
	}

}
