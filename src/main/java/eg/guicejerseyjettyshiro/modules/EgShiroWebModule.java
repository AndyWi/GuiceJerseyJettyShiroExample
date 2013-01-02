package eg.guicejerseyjettyshiro.modules;

import javax.servlet.ServletContext;

import org.apache.shiro.guice.web.ShiroWebModule;

import com.google.inject.name.Names;

import eg.guicejerseyjettyshiro.realms.EgAuthorizingRealm;

public class EgShiroWebModule extends ShiroWebModule {

	public EgShiroWebModule(ServletContext servletContext) {
		super(servletContext);
	}

	@Override
	protected void configureShiroWeb() {
		bindConstant().annotatedWith(Names.named("shiro.globalSessionTimeout")).to(30000L);

		bindRealm().to(EgAuthorizingRealm.class).asEagerSingleton();

		addFilterChain("/login.jsp", AUTHC);
		addFilterChain("/api/*", AUTHC);
	}

}
