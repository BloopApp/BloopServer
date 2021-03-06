package website.bloop.server.jdbi;

import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import website.bloop.server.api.NearbyFlag;
import website.bloop.server.api.PlayerLocation;

@RegisterMapper(NearbyFlagMapper.class)
public interface NearbyFlagDAO {

    @SqlQuery("SELECT st_distance(location, st_setsrid(st_makepoint(:latitude, :longitude), 4326)) AS distance, flag_id, color " +
              "FROM flag WHERE is_captured = FALSE AND player_id != " +
              "(SELECT player_id FROM player WHERE google_play_id = :googlePlayId) " +
              "ORDER BY distance LIMIT 1")
    NearbyFlag getNearestFlag(@BindBean PlayerLocation location);
}
