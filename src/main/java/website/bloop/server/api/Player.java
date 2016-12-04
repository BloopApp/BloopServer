package website.bloop.server.api;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Player {
	@JsonProperty
	private long playerId;
	
	@NotNull
	@JsonProperty
	private String name;
	
	public Player() { }
	
	public Player(long playerId, String name) {
		super();
		this.playerId = playerId;
		this.name = name;
	}

	public long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
