package website.bloop.server.jdbi;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

import website.bloop.server.api.Player;

public interface PlayerDAO {
    @SqlUpdate("INSERT INTO player (name, google_play_id) " +
               "SELECT :name, :googlePlayId " +
               "WHERE NOT EXISTS " +
               "(SELECT 1 FROM player WHERE google_play_id = :googlePlayId)")
    @GetGeneratedKeys
    int addPlayer(@BindBean Player player);
    
    @SqlQuery("SELECT EXISTS (SELECT 1 FROM flag WHERE player_id = " +
              "(SELECT player_id FROM player WHERE google_play_id = :googlePlayId) " +
              "AND captured = FALSE)")
    boolean hasFlag(@Bind("googlePlayId") String googlePlayId);
    
    @SqlUpdate("UPDATE player SET firebase_token = :firebaseToken " +
               "WHERE google_play_id = :googlePlayId")
    void updateFirebaseToken(@Bind("firebaseToken") Player player);
    
    @SqlQuery("SELECT name FROM player JOIN flag USING (player_id) " +
              "WHERE flag_id = :flagId")
    String getPlayerName(@Bind("flagId") long flagId);
    
    @SqlQuery("SELECT count(*) FROM flag " +
              "WHERE capturing_player_id = " +
              "(SELECT player_id FROM player WHERE google_play_id = :googlePlayId)")
    int getFlagsCapturedByPlayer(@Bind("googlePlayId") String googlePlayId);
}
