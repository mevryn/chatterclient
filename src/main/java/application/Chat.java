package application;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Chat extends JTextArea {
    private List<Message> chatHistory = new ArrayList<>();
    public Chat(){
        setEditable(false);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Message message:chatHistory){
            sb.append(message.toString());
        }
        return sb.toString();
    }

    public List<Message> getChatHistory() {
        return chatHistory;
    }

    private void setChatHistory(List<Message> chatHistory) {
        this.chatHistory = chatHistory;
    }

    private void chatSetUp(){
        setText(chatHistory.toString());
    }
    public void sendMessage(Message message){
        chatHistory.add(message);
        chatSetUp();
    }
}
