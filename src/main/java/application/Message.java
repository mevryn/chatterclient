package application;


import java.io.Serializable;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Message implements Serializable {
    User user;
    String message;
    String time;

    Message(User user, String message) {
        this.user = user;
        this.message = message;
        this.time = parseDate();
    }

    @Override
    public String toString() {
        return time + " " + user +
                ": " + message + '\n';
    }

    private String parseDate() {
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.systemDefault());
        String time = zonedDateTime.format(DateTimeFormatter.ofPattern("HH:mm"));
        return time;
    }
}
