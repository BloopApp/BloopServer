package website.bloop.server.jdbi;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import website.bloop.server.api.Flag;

@RegisterMapper(FlagMapper.class)
public interface FlagDAO {
	@SqlQuery("SELECT *, st_x(location::geometry), st_y(location::geometry) " +
			  "FROM flag WHERE flag_id = :flagId")
	Flag getFlag(@Bind("flagId") int id);
	
	@SqlUpdate("INSERT INTO flag (player_id, location) " +
			   "VALUES (:playerId, st_setsrid(st_makepoint(:latitude, :longitude), 4326))")
	@GetGeneratedKeys
	int insertFlag(@BindBean Flag flag);
	
	@SqlUpdate("UPDATE flag SET (is_captured, time_captured, capturing_player_id) = " +
			   "(TRUE, now(), :capturingPlayerId) " +
			   "WHERE flag_id = :flagId")
	void captureFlag(@BindBean Flag flag);
	
	@SqlUpdate("UPDATE flag SET (location) = " +
			   "(st_setsrid(st_makepoint(:latitude, :longitude), 4326)) " +
			   "WHERE flag_id = :flagId")
	void moveFlag(@BindBean Flag flag);
}
