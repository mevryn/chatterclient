package application;

import javax.swing.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Chat extends JTextArea implements ChatListener {
    private List<Message> chatHistory = new ArrayList<>();

    protected Chat() {
        setEditable(false);
    }

    private String parseDate(ZonedDateTime zonedDateTime) {
        return zonedDateTime.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    private void sortMessages() {
        chatHistory.sort(Comparator.comparing(Message::getTime));
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
  /*  @Override
    public void getNewMessage(){
        chatHistory.add(host.receiveNewMessageFromServer());
        setText(printChat());
    }*/
}