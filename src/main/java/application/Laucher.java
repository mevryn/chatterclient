package application;

import network.Host;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Laucher extends JFrame {
        JTextField nickNameInput=new JTextField();
        JTextField hostNameInput=new JTextField();
        JTextField portInput=new JTextField();
        GridLayout laucherLayout = new GridLayout(0,4);
        JButton joinButton = new JButton();
        Host host;
    public Laucher() {
        defaultSetUp();
    }
    private void defaultSetUp(){
        setLayout(laucherLayout);
        setSize(new Dimension(500, 100));
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new JLabel("Nickname"));
        add(nickNameInput);
        add(new JLabel("Host name"));
        add(hostNameInput);
        add(new JLabel("Port"));
        add(portInput);
        add(joinButton);
        configureButton();
        nickNameInput.setPreferredSize(new Dimension(60,20));
        hostNameInput.setPreferredSize(new Dimension(60,20));
        portInput.setPreferredSize(new Dimension(40,20));
    }
    private void configureButton() {
        joinButton.setText("Join Chat");
        joinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(hostNameInput.getText().equals("") || portInput.getText().equals("") || nickNameInput.getText().equals("")))
                    new MainWindow(new User(nickNameInput.getText()), new Host(hostNameInput.getText(), portInput.getText()), new Chat());
            }
        });
    }
}
