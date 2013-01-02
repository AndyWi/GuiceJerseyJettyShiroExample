package eg.guicejerseyjettyshiro.services;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import eg.guicejerseyjettyshiro.dao.UuidDao;

@Path("/api/uuid")
@Produces({MediaType.APPLICATION_XML})
public class UuidService {

	private final UuidDao uuidDao;

	@Inject
	public UuidService(UuidDao uuidDao) {
		this.uuidDao = uuidDao;
	}

	@GET
	public String get() {
		Subject currentUser = SecurityUtils.getSubject();
		System.out.println("UuidService current user: " + currentUser.getPrincipal().toString());
		return "<uuid>" + this.uuidDao.generateUuid().toString() + "</uuid>";
	}

}
