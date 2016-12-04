package website.bloop.server.jdbi;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import website.bloop.server.api.Player;

public class PlayerMapper implements ResultSetMapper<Player> {
	public Player map(int index, ResultSet r, StatementContext ctx) throws SQLException {
		return new Player(r.getLong("player_id"), r.getString("name"));
	}
}
