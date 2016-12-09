package website.bloop.server.jdbi;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import website.bloop.server.api.NearbyFlag;

public class NearbyFlagMapper implements ResultSetMapper<NearbyFlag> {
	public NearbyFlag map(int index, ResultSet r, StatementContext ctx) throws SQLException {
		return new NearbyFlag(r.getDouble("distance"), r.getLong("flag_id"));
	}
}
