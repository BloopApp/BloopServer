package website.bloop.server.resources;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import website.bloop.server.api.CapturedFlag;
import website.bloop.server.api.NearbyFlag;
import website.bloop.server.api.PlacedFlag;
import website.bloop.server.api.PlayerLocation;
import website.bloop.server.jdbi.FlagDAO;
import website.bloop.server.jdbi.NearbyFlagDAO;
import website.bloop.server.jdbi.PlayerDAO;
import website.bloop.server.notifications.FlagCaptureData;
import website.bloop.server.notifications.FlagCapturePayload;

@Path("/flag")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FlagResource {
    FlagDAO flagDAO;
    NearbyFlagDAO nearbyFlagDAO;
    PlayerDAO playerDAO;
    Client client;
    String firebaseKey;
    
    public FlagResource(FlagDAO flagDAO, NearbyFlagDAO nearbyFlagDAO, PlayerDAO playerDAO,
                        Client client, String firebaseKey) {
        this.flagDAO = flagDAO;
        this.nearbyFlagDAO = nearbyFlagDAO;
        this.playerDAO = playerDAO;
        this.client = client;
        this.firebaseKey = firebaseKey;
    }
    
    @POST
    @Path("/place")
    public Response addFlag(@Valid PlacedFlag flag) {
        if (!playerDAO.hasFlag(flag.getGooglePlayId())) {
            flagDAO.insertFlag(flag);
            return Response.ok().build();
        } else {
            return Response.status(403).build();
        }
    }
    
    @POST
    @Path("/nearby")
    public NearbyFlag getNearestFlag(@Valid PlayerLocation location) {
        NearbyFlag flag = nearbyFlagDAO.getNearestFlag(location);
        if (flag != null) {
            if (flag.getBloopFrequency() == NearbyFlag.MAX_FREQUENCY) {
                flag.setPlayerName(playerDAO.getPlayerName(flag.getFlagId()));
            }
        } else {
            flag = new NearbyFlag();
            flag.setBloopFrequency(0);
        }
        return flag;
    }

    @POST
    @Path("/capture")
    public Response captureFlag(@Valid CapturedFlag flag) throws JsonProcessingException {
        if (!playerDAO.hasFlag(flag.getCapturingPlayerId())) {
            return Response.status(403).build();
        }
        flagDAO.captureFlag(flag);
        
        String capturingPlayerName = playerDAO.getPlayerName(flag.getCapturingPlayerId());
        String flagOwnerFirebaseToken = playerDAO.getFirebaseToken(flag.getFlagId());
        
        ObjectMapper mapper = new ObjectMapper();
        FlagCapturePayload payload = new FlagCapturePayload(flagOwnerFirebaseToken,
                new FlagCaptureData(capturingPlayerName));
        String payloadString = mapper.writeValueAsString(payload);

        client.target("https://fcm.googleapis.com/fcm/send")
              .request(MediaType.APPLICATION_JSON_TYPE)
              .header("Authorization", "key=" + firebaseKey)
              .post(Entity.entity(payloadString, MediaType.APPLICATION_JSON), Response.class);
        return Response.ok().build();
    }
}
