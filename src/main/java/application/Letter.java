package application;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Letter {

    private User user;
    private String message;
    private String time;
    public Letter(User user, String message, ZonedDateTime zonedDateTime){
            this.user = user;
            this.message = message;
            time = DateTimeFormatter.ISO_DATE_TIME.format(zonedDateTime);
    }
    public Message parseToMessage(){
        return new Message(this.user,this.message,this.time);
    }

}
