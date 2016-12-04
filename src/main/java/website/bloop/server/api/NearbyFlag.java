package website.bloop.server.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NearbyFlag {
	public static final int CAPTURE_DISTANCE = 10;
	public static final int MAX_FREQUENCY = 10;
	public static final int MAX_DISTANCE = 1000;
	
	@JsonProperty
    private double bloopFrequency;

    @JsonProperty
    private long flagId;

    @JsonProperty
    private String playerName;

    public NearbyFlag(double distance, long flagId) {
        this.bloopFrequency = calculateBloopFrequency(distance);
        this.flagId = flagId;
    }

    public NearbyFlag() { }

    public double getBloopFrequency() {
		return bloopFrequency;
	}

	public void setBloopFrequency(double bloopFrequency) {
		this.bloopFrequency = bloopFrequency;
	}

	public long getFlagId() {
        return flagId;
    }

    public void setFlagId(long flagId) {
        this.flagId = flagId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    
    public double calculateBloopFrequency(double distance) {
    	if (distance < CAPTURE_DISTANCE) {
    		return MAX_FREQUENCY;
    	}
    	if (distance > MAX_DISTANCE) {
    		distance = MAX_DISTANCE;
    	}
    	return CAPTURE_DISTANCE * MAX_FREQUENCY / distance;
    }
}
