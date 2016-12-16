package website.bloop.server.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NearbyFlag {
    public static final int CAPTURE_DISTANCE = 10;
    public static final double MAX_FREQUENCY = 10;
    public static final int MAX_DISTANCE = 1000;
    public static final double MIN_FREQUENCY = CAPTURE_DISTANCE * MAX_FREQUENCY / MAX_DISTANCE;
    
    @JsonProperty
    private double bloopFrequency;

    @JsonProperty
    private long flagId;

    @JsonProperty
    private String playerName;
    
    @JsonProperty
    private int color;

    public NearbyFlag(double distance, long flagId, int color) {
        this.bloopFrequency = calculateBloopFrequency(distance);
        this.flagId = flagId;
        this.color = color;
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

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public double calculateBloopFrequency(double distance) {
        if (distance < CAPTURE_DISTANCE) {
            return MAX_FREQUENCY;
        }
        if (distance > MAX_DISTANCE) {
            return 0;
        }
        return CAPTURE_DISTANCE * MAX_FREQUENCY / distance;
    }
}
