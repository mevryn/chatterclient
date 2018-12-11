package application;

import javax.swing.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Chat extends JTextArea implements ChatListener {
    private List<Message> chatHistory = new ArrayList<Message>();

    public Chat() {
        setEditable(false);
    }

    private String parseDate(ZonedDateTime zonedDateTime) {
        String time = zonedDateTime.format(DateTimeFormatter.ofPattern("HH:mm"));
        return time;
    }

    private void sortMessages() {
        Collections.sort(chatHistory, new Comparator<Message>() {
            @Override
            public int compare(Message o1, Message o2) {
                if (o1.getTime().toLocalDateTime().isBefore(o2.getTime().toLocalDateTime())) {
                    return -1;
                } else if (o2.getTime().toLocalDateTime().isBefore(o1.getTime().toLocalDateTime())) {
                    return 1;
                } else
                    return 0;
            }
        });
    }

    private String printChat() {
        StringBuilder stringBuilder = new StringBuilder();
        sortMessages();
        for (Message message : chatHistory) {
            stringBuilder.append(parseDate(message.getTime()) + " " + message.getUser().getNickName() + ": " + message.getMessage() + "\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public void newMessageAppeared(Message message) {
        chatHistory.add(message);
        setText(printChat());
    }

}