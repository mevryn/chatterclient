package application;

import network.Host;
import network.HostFactory;

import javax.swing.*;
import java.awt.*;

public class Laucher extends JFrame {

    private JTextField nickNameInput = new JTextField();
    private JTextField hostNameInput = new JTextField();
    private JTextField portInput = new JTextField();

    private GridLayout laucherLayout = new GridLayout(0, 4);

    private JButton joinButton = new JButton();

    public Laucher() {
        defaultSetUp();
    }

    private void defaultSetUp() {
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
        nickNameInput.setPreferredSize(new Dimension(60, 20));
        hostNameInput.setPreferredSize(new Dimension(60, 20));
        portInput.setPreferredSize(new Dimension(40, 20));
    }

    private void configureButton() {
        joinButton.setText("Join Chat");
        joinButton.addActionListener(e -> {
            Host host = HostFactory.newHostForConfiguration(hostNameInput.getText(), portInput.getText());
            new MainWindow(new User(nickNameInput.getText()), new Chat(), host);
        });
    }

}
