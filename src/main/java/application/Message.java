package application;



import javax.xml.crypto.Data;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Message {
    User user;
    String message;
    String time;
    Message(User user, String message){
        this.user = user;
        this.message = message;
        this.time =parseDate();
    }

    @Override
    public String toString() {
        return time +" " + user +
                ": " + message + '\n';
    }

    private String parseDate(){
        ZonedDateTime zonedDateTime=ZonedDateTime.now(ZoneId.systemDefault());
        String time = new String();
        time = zonedDateTime.format(DateTimeFormatter.ofPattern("HH:mm"));
        return time;
    }
}
