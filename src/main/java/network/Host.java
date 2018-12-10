package network;

import javax.swing.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Host {
    private String hostname = new String();
    private String portNumber = new String();
    private DataInputStream dis;
    private DataOutputStream dos;
    private Socket socket;
    public Host(String hostname, String portNumber) {
        this.hostname = hostname;
        this.portNumber = portNumber;
        init();
    }

    public DataInputStream getDis() {
        return dis;
    }

    public void setDis(DataInputStream dis) {
        this.dis = dis;
    }

    public DataOutputStream getDos() {
        return dos;
    }

    public void setDos(DataOutputStream dos) {
        this.dos = dos;
    }

    private void init(){
        try {
            InetAddress addr = InetAddress.getByName(this.hostname);
            int port = Integer.parseInt(portNumber);
            socket = new Socket(addr,port);
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
        }
        catch(UnknownHostException uHE){
            JLabel textMassage = new JLabel();
            textMassage.setText(uHE.getStackTrace().toString());
            textMassage.setVisible(true);
        }
        catch(IOException iOE){
            JLabel textMassage = new JLabel();
            textMassage.setText(iOE.getStackTrace().toString());
            textMassage.setVisible(true);
        }
        }
}
