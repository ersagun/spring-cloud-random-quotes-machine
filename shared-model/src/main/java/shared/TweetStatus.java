package shared;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import twitter4j.Status;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TweetStatus {

    @Id
    private String id;
    private Status status;


    public TweetStatus() {
    }

    public TweetStatus(String id, Status status) {
        this.id = id;
        this.status = status;
    }

    public TweetStatus(Status status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
