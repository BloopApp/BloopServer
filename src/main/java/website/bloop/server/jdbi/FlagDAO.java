package website.bloop.server.jdbi;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import website.bloop.server.api.CapturedFlag;
import website.bloop.server.api.Flag;
import website.bloop.server.api.PlacedFlag;

@RegisterMapper(FlagMapper.class)
public interface FlagDAO {
    @SqlQuery("SELECT *, st_x(location::geometry), st_y(location::geometry) " +
              "FROM flag WHERE player_id = " +
              "(SELECT player_id FROM player WHERE google_play_id = :googlePlayId)")
    Flag getFlag(@Bind("googlePlayId") String googlePlayId);
    
    @SqlUpdate("INSERT INTO flag (player_id, location, color) " +
               "VALUES ((SELECT player_id FROM player WHERE google_play_id = :googlePlayId), " +
               "st_setsrid(st_makepoint(:latitude, :longitude), 4326), :color)")
    @GetGeneratedKeys
    int insertFlag(@BindBean PlacedFlag flag);
    
    @SqlUpdate("DELETE FROM flag WHERE player_id = " +
               "(SELECT player_id FROM player WHERE google_play_id = :googlePlayId) " +
               "AND is_captured = FALSE")
    void deleteFlag(@Bind("googlePlayId") String googlePlayId);
    
    @SqlUpdate("UPDATE flag SET (is_captured, time_captured, capturing_player_id) = " +
               "(TRUE, now(), " +
               "(SELECT player_id FROM player WHERE google_play_id = :capturingPlayerId)) " +
               "WHERE flag_id = :flagId")
    void captureFlag(@BindBean CapturedFlag flag);
    
    @SqlUpdate("UPDATE flag SET (location) = " +
               "(st_setsrid(st_makepoint(:latitude, :longitude), 4326)) " +
               "WHERE flag_id = :flagId")
    void moveFlag(@BindBean Flag flag);
}
