package application;

import javax.swing.*;
import java.awt.*;

public class ErrorWindow extends JFrame {

    private JTextArea errorField = new JTextArea();

    public ErrorWindow(String errorMessage) {
        errorField.setText(errorMessage);
        errorField.setEditable(false);
        setSize(new Dimension(100, 100));
        add(errorField);
        setVisible(true);
    }
}
