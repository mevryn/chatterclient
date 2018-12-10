package application;

import network.Host;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Chat extends JTextArea {
    private List<Message> chatHistory = new ArrayList<>();
    private Host host;
    public Chat(Host host) {
        setEditable(false);
        this.host = host;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Message message : chatHistory) {
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

    private void chatSetUp() {
        setText(chatHistory.toString());
    }

    public void sendMessage(Message message) {
        chatHistory.add(message);
        chatSetUp();
    }
}
