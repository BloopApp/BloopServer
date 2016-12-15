package website.bloop.server.resources;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import website.bloop.server.api.Flag;
import website.bloop.server.api.OwnFlag;
import website.bloop.server.api.Player;
import website.bloop.server.jdbi.FlagDAO;
import website.bloop.server.jdbi.PlayerDAO;

@Path("/players")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PlayerResource {
    PlayerDAO playerDAO;
    FlagDAO flagDAO;
    
    public PlayerResource(PlayerDAO playerDAO, FlagDAO flagDAO) {
        this.playerDAO = playerDAO;
        this.flagDAO = flagDAO;
    }
    
    @POST
    @Path("/new")
    public int addNewPlayer(@Valid Player player) {
        return playerDAO.addPlayer(player);
    }
    
    @POST
    @Path("/update-firebase")
    public Response updateFirebaseToken(@Valid Player player) {
        playerDAO.updateFirebaseToken(player);
        return Response.ok().build();
    }
    
    @GET
    @Path("/num-flags")
    public int getCapturedFlags(@QueryParam("id") String id) {
        return playerDAO.getFlagsCapturedByPlayer(id);
    }
    
    @GET
    @Path("/has-flag")
    public OwnFlag hasPlacedFlag(@QueryParam("id") String id) {
        Boolean hasFlag = playerDAO.hasFlag(id);
        OwnFlag ownFlag;
        if (hasFlag) {
            Flag flag = flagDAO.getFlag(id);
            ownFlag = new OwnFlag(hasFlag, flag.getFlagId(), flag.getLatitude(), flag.getLongitude());
        } else {
            ownFlag = new OwnFlag();
            ownFlag.setDoesExist(hasFlag);
        }
        return ownFlag;
    }
    
    @GET
    @Path("/delete-flag")
    public Response deleteFlag(@QueryParam("id") String id) {
        flagDAO.deleteFlag(id);
        return Response.ok().build();
    }
}
