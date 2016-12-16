package website.bloop.server.notifications;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FlagCapturePayload {
    @JsonProperty
    private String to;
    
    @JsonProperty
    private FlagCaptureData notification;

    public FlagCapturePayload(String to, FlagCaptureData notification) {
        super();
        this.to = to;
        this.notification = notification;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public FlagCaptureData getNotification() {
        return notification;
    }

    public void setNotification(FlagCaptureData notification) {
        this.notification = notification;
    }
}
