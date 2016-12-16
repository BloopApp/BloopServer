package website.bloop.server.notifications;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FlagCaptureData {
    @JsonProperty
    private String title;
    
    @JsonProperty
    private String body;

    public FlagCaptureData(String playerName) {
        super();
        this.title = "Your flag has been captured!";
        this.body = playerName + " has your flag.";
    }

    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
