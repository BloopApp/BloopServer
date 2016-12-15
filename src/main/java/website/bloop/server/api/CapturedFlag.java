package website.bloop.server.api;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CapturedFlag {
    @NotNull
    @JsonProperty
    private long flagId;

    @NotNull
    @JsonProperty
    private String capturingPlayerId;

    public CapturedFlag() { }

    public long getFlagId() {
        return flagId;
    }

    public void setFlagId(long flagId) {
        this.flagId = flagId;
    }

    public String getCapturingPlayerId() {
        return capturingPlayerId;
    }

    public void setCapturingPlayerId(String capturingPlayerId) {
        this.capturingPlayerId = capturingPlayerId;
    }
}
