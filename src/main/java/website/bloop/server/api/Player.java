package website.bloop.server.api;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public class Player {
	@JsonProperty
	private Long playerId;
	
	@JsonProperty
	private String name;
	
	@NotNull
	@JsonProperty
	private String googlePlayId;
	
	@JsonProperty
	private String firebaseToken;
	
	public Player() { }
	
	public Player(Long playerId, String name, String googlePlayId, String firebaseToken) {
		super();
		this.playerId = playerId;
		this.name = name;
		this.googlePlayId = googlePlayId;
		this.firebaseToken = firebaseToken;
	}

	public Long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGooglePlayId() {
		return googlePlayId;
	}

	public void setGooglePlayId(String googlePlayId) {
		this.googlePlayId = googlePlayId;
	}

	public String getFirebaseToken() {
		return firebaseToken;
	}

	public void setFirebaseToken(String firebaseToken) {
		this.firebaseToken = firebaseToken;
	}
}
