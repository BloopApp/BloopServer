package website.bloop.server.jdbi;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import website.bloop.server.api.Flag;

@RegisterMapper(FlagMapper.class)
public interface FlagDAO {
	@SqlQuery("SELECT flag_id, player_id, ST_X(location::geometry), ST_Y(location::geometry) " +
			  "FROM flag WHERE flag_id = :flagId")
	List<Flag> getFlag(@Bind("flagId") int id);
	
	@SqlUpdate("INSERT INTO flag (player_id, location) " +
			   "VALUES (:playerId, ST_SetSRID(ST_MakePoint(:latitude, :longitude), 4326))")
	void insertFlag(@BindBean Flag flag);
}
