import application.Laucher;
import application.MainWindow;
import application.User;

import java.awt.*;

public class Main {
    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Laucher();
            }
        });
    }
}
