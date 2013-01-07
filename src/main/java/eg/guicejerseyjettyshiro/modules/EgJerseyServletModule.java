package eg.guicejerseyjettyshiro.modules;

import org.apache.shiro.guice.web.GuiceShiroFilter;
import org.eclipse.jetty.servlet.DefaultServlet;

import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

import eg.guicejerseyjettyshiro.dao.UuidDao;
import eg.guicejerseyjettyshiro.services.UuidService;

public class EgJerseyServletModule extends JerseyServletModule {

	@Override
	protected void configureServlets() {
		bindings();
		filters();
	}

	private void bindings() {
		bind(UuidDao.class);
		bind(UuidService.class);
		bind(DefaultServlet.class).asEagerSingleton();
		bind(GuiceContainer.class).asEagerSingleton();
		serve("/*").with(DefaultServlet.class);
	}

	private void filters() {
		filter("/*").through(GuiceShiroFilter.class);
		filter("/api/*").through(GuiceContainer.class);
	}

}
