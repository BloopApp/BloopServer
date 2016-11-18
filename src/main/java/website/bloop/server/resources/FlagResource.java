package website.bloop.server.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import website.bloop.server.api.Flag;
import website.bloop.server.jdbi.FlagDAO;

@Path("/flags")
@Produces({MediaType.APPLICATION_JSON})
public class FlagResource {
	FlagDAO dao;
	
	public FlagResource(FlagDAO dao) {
		this.dao = dao;
	}
	
	@GET
	@Path("{id}")
	public List<Flag> getFlag(@PathParam("id") String id) {
		return dao.getFlag(Integer.parseInt(id));
	}
}
