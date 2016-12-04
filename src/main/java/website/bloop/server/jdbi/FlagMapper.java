package website.bloop.server.jdbi;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import website.bloop.server.api.Flag;

public class FlagMapper implements ResultSetMapper<Flag> {
	public Flag map(int index, ResultSet r, StatementContext ctx) throws SQLException {
		return new Flag(r.getLong("flag_id"), r.getLong("player_id"),
						r.getDouble("st_x"), r.getDouble("st_y"), 
						r.getTimestamp("time_placed"),
						r.getBoolean("is_captured"), r.getTimestamp("time_captured"), 
						r.getLong("capturing_player_id"));
	}
}
