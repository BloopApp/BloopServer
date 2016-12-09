package website.bloop.server.api;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PlayerLocation {
	
	@NotNull
	@JsonProperty
	private String googlePlayId;
	
	@NotNull
	@JsonProperty
	private double latitude;
	
	@NotNull
	@JsonProperty
	private double longitude;
	
	public PlayerLocation() { }

	public String getGooglePlayId() {
		return googlePlayId;
	}

	public void setGooglePlayId(String playerId) {
		this.googlePlayId = playerId;
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
