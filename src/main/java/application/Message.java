package application;


import java.io.Serializable;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Message implements Serializable {

    private User user;
    private String message;
    private ZonedDateTime time;
    public Message(User user, String message) {
        this.user = user;
        this.message = message;
        this.time = ZonedDateTime.now(ZoneId.systemDefault());
    }
    public Message(User user,String message,String date) {
        this.user = user;
        this.message = message;
        this.time = time.parse(date);
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTime(ZonedDateTime time) {
        this.time = time;
    }

    public Message() {
    }

    public User getUser() {
        return user;
    }

    public String getMessage() {
        return message;
    }

    public ZonedDateTime getTime() {
        return time;
    }


}
