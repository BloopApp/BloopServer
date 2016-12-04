package website.bloop.server.resources;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import website.bloop.server.api.Flag;
import website.bloop.server.api.NearbyFlag;
import website.bloop.server.api.PlayerLocation;
import website.bloop.server.jdbi.FlagDAO;
import website.bloop.server.jdbi.NearbyFlagDAO;
import website.bloop.server.jdbi.PlayerDAO;

@Path("/flag")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FlagResource {
	FlagDAO flagDAO;
	NearbyFlagDAO nearbyFlagDAO;
	PlayerDAO playerDAO;
	
	public FlagResource(FlagDAO flagDAO, NearbyFlagDAO nearbyFlagDAO, PlayerDAO playerDAO) {
		this.flagDAO = flagDAO;
		this.nearbyFlagDAO = nearbyFlagDAO;
		this.playerDAO = playerDAO;
	}
	
	@POST
	@Path("/place")
	public int addFlag(Flag flag) {
		return flagDAO.insertFlag(flag);
	}
	
	@GET
	@Path("/{id}")
	public Flag getFlag(@PathParam("id") String id) {
		return flagDAO.getFlag(Integer.parseInt(id));
	}
	
	@POST
	@Path("/nearby")
	public NearbyFlag getNearestFlag(@Valid PlayerLocation location) {
		NearbyFlag flag = nearbyFlagDAO.getNearestFlag(location);
		if (flag.getBloopFrequency() == NearbyFlag.MAX_FREQUENCY) {
			flag.setPlayerName(playerDAO.getPlayerName(flag.getFlagId()));
		}
		return flag;
	}
	
	@POST
	@Path("/capture")
	public void captureFlag(@Valid Flag flag) {
		flagDAO.captureFlag(flag);
	}
}
