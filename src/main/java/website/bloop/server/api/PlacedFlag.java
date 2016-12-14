package website.bloop.server.api;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PlacedFlag {
    @NotNull
    @JsonProperty
    private String googlePlayId;

    @NotNull
    @JsonProperty
    private double latitude;

    @NotNull
    @JsonProperty
    private double longitude;

    @NotNull
    @JsonProperty
    private int color;

    public PlacedFlag(String googlePlayId, double latitude, double longitude, int color) {
        this.googlePlayId = googlePlayId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.color = color;
    }

    public PlacedFlag() { }

    public String getGooglePlayId() {
        return googlePlayId;
    }

    public void setGooglePlayId(String googlePlayId) {
        this.googlePlayId = googlePlayId;
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

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
