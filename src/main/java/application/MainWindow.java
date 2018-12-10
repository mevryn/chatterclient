package application;

import network.Host;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    private JTextField inputField = new JTextField();
    private JTextArea textField = new JTextArea();
    private JScrollPane scrolledtextField;
    private FlowLayout chatLayout = new FlowLayout();
    private JButton sendButton = new JButton();
    private Chat chat;
    private User user;
    private Host host;
    public MainWindow(User user, Host host,Chat chat){
        this.chat = chat;
        this.user=user;
        this.host=host;
        scrolledtextField= new JScrollPane(chat);
        pack();
        this.setTitle("Chatter "+user.getNickName());
        settingsSetUp();
        chatInterfaceSetUp();
        setVisible(true);
    }
    private void settingsSetUp(){
        setResizable(false);
        setSize(new Dimension(600,600));
        setLayout(chatLayout);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    private void chatInterfaceSetUp(){

        inputFieldSetUp();
        textFieldSetUp();
        sendButtonSetUp();
        add(scrolledtextField);
        add(inputField);
        add(sendButton);
    }
    private void sendButtonSetUp(){
        sendButton.setText("Send");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
    }
    private void inputFieldSetUp(){
        inputField.setPreferredSize(new Dimension (420,20));
        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
    }
    private void textFieldSetUp(){
        scrolledtextField.setPreferredSize(new Dimension(500,500));
        scrolledtextField.createVerticalScrollBar();
        textField.setEditable(false);
    }
    private void sendMessage() {
        if (!inputField.getText().isEmpty()) {
            String textOfMessage = inputField.getText();
            Message message = new Message(user, textOfMessage);
            chat.sendMessage(message);
            inputField.setText("");
        }
    }
}