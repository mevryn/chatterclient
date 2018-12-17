package application;

import network.Host;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class MainWindow extends JFrame implements Subject {
    private JTextField inputField = new JTextField();
    private JTextArea textField = new JTextArea();
    private JScrollPane scrolledtextField;
    private FlowLayout chatLayout = new FlowLayout();
    private JButton sendButton = new JButton();

    private StringWriter writer = new StringWriter();
    private PrintWriter printWriter = new PrintWriter(writer);

    private List<ChatListener> observers = new ArrayList<>();

    private Host host;
    private User user;
    private Chat chat;

    protected MainWindow(User user, Chat chat, Host host) {
        this.user = user;
        this.host = host;
        this.chat = chat;
        scrolledtextField = new JScrollPane(chat);
        addListener(chat);
        pack();
        this.setTitle("Chatter " + user.getNickName());
        closingOperation();
        settingsSetUp();
        chatInterfaceSetUp();
        setVisible(true);
        new Thread(() -> notifyObservers(host.receiveNewMessageFromServer())).start();

    }

    private void addListener(ChatListener chatListener) {
        observers.add(chatListener);
    }

    private void settingsSetUp() {
        setResizable(false);
        setSize(new Dimension(600, 600));
        setLayout(chatLayout);
        closingOperation();
    }

    private void closingOperation() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    if (host.getSocket() != null)
                        host.getSocket().close();
                    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                } catch (IOException IOE) {
                    IOE.printStackTrace(printWriter);
                    new ErrorWindow(writer.toString());
                }
            }
        });
    }

    private void chatInterfaceSetUp() {

        inputFieldSetUp();
        textFieldSetUp();
        sendButtonSetUp();
        add(scrolledtextField);
        add(inputField);
        add(sendButton);
    }

    private void sendButtonSetUp() {
        sendButton.setText("Send");
        sendButton.addActionListener(e -> newMessage());
    }

    private void inputFieldSetUp() {
        inputField.setPreferredSize(new Dimension(420, 20));
        inputField.addActionListener(e -> newMessage());
    }

    private void textFieldSetUp() {
        scrolledtextField.setPreferredSize(new Dimension(500, 500));
        scrolledtextField.createVerticalScrollBar();
        textField.setEditable(false);
    }

    @Override
    public void register(ChatListener chatListener) {
        observers.add(chatListener);
    }

    @Override
    public void unregister(ChatListener chatListener) {
        observers.remove(chatListener);
    }

    @Override
    public void notifyObservers(Message message) {
        for (ChatListener chatListener : observers) {
            chatListener.newMessageAppeared(message);
        }
    }

    private void newMessage() {
        if (!inputField.getText().isEmpty()) {
            String textOfMessage = inputField.getText();
            Message message = new Message(user, textOfMessage);
            notifyObservers(message);
            host.sendMessageToServer(message);
            inputField.setText("");
        }
    }

}