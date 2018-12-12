package application;


import java.io.Serializable;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Message implements Serializable {

    private User user;
    private String message;
    private ZonedDateTime time;

    protected User getUser() {
        return user;
    }

    protected String getMessage() {
        return message;
    }

    protected ZonedDateTime getTime() {
        return time;
    }


    public Message(User user, String message) {
        this.user = user;
        this.message = message;
        this.time = ZonedDateTime.now(ZoneId.systemDefault());
    }


}
