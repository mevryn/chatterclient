package application;


import java.io.Serializable;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Message implements Serializable {

    private User user;
    private String message;
    private ZonedDateTime time;

    public User getUser() {
        return user;
    }

    public String getMessage() {
        return message;
    }

    public ZonedDateTime getTime() {
        return time;
    }


    Message(User user, String message) {
        this.user = user;
        this.message = message;
        this.time = ZonedDateTime.now(ZoneId.systemDefault());
    }


}
