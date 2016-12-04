package website.bloop.server.api;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Flag {
	@JsonProperty
	private long flagId;
	
	@JsonProperty
	private long playerId;
	
	@JsonProperty
	private double latitude;
	
	@JsonProperty
	private double longitude;
	
	@JsonProperty
	private Timestamp timePlaced;
	
	@JsonProperty
	private boolean isCaptured;
	
	@JsonProperty
	private Timestamp timeCaptured;
	
	@JsonProperty
	private long capturingPlayerId;
	
	public Flag() { }

	public Flag(long flagId, long playerId, double latitude, double longitude, Timestamp timePlaced, 
				boolean isCaptured, Timestamp timeCaptured, long capturingPlayerId) {
		this.flagId = flagId;
		this.playerId = playerId;
		this.latitude = latitude;
		this.longitude = longitude;
		this.timePlaced = timePlaced;
		this.isCaptured = isCaptured;
		this.timeCaptured = timeCaptured;
		this.capturingPlayerId = capturingPlayerId;
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

	public Timestamp getTimePlaced() {
		return timePlaced;
	}

	public void setTimePlaced(Timestamp timePlaced) {
		this.timePlaced = timePlaced;
	}

	public boolean isCaptured() {
		return isCaptured;
	}

	public void setCaptured(boolean isCaptured) {
		this.isCaptured = isCaptured;
	}

	public Timestamp getTimeCaptured() {
		return timeCaptured;
	}

	public void setTimeCaptured(Timestamp timeCaptured) {
		this.timeCaptured = timeCaptured;
	}

	public long getCapturingPlayerId() {
		return capturingPlayerId;
	}

	public void setCapturingPlayerId(long capturingPlayerId) {
		this.capturingPlayerId = capturingPlayerId;
	}
}
