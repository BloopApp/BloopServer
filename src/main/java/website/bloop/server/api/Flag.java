package website.bloop.server.api;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Flag {
	@JsonProperty
	private long flagId;
	
	@NotNull
	@JsonProperty
	private long playerId;
	
	@NotNull
	@JsonProperty
	private double latitude;
	
	@NotNull
	@JsonProperty
	private double longitude;
	
	public Flag() { }
	
	public Flag(long flagId, long playerId, double latitude, double longitude) {
		super();
		this.flagId = flagId;
		this.playerId = playerId;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public long getFlagId() {
		return flagId;
	}

	public void setFlagId(long flagId) {
		this.flagId = flagId;
	}

	public long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
}
