package website.bloop.server.resources;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import website.bloop.server.api.Player;
import website.bloop.server.jdbi.PlayerDAO;

@Path("/players")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PlayerResource {
    PlayerDAO dao;
    
    public PlayerResource(PlayerDAO dao) {
        this.dao = dao;
    }
    
    @POST
    @Path("/new")
    public int addNewPlayer(@Valid Player player) {
        return dao.addPlayer(player);
    }
    
    @POST
    @Path("/update-firebase")
    public void updateFirebaseToken(@Valid Player player) {
        dao.updateFirebaseToken(player);
    }
    
    @GET
    @Path("/{id}/numflags")
    public int getCapturedFlags(@PathParam("id") String id) {
        return dao.getFlagsCapturedByPlayer(Integer.parseInt(id));
    }
}
