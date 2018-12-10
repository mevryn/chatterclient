package network;

import application.ErrorWindow;
import application.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
    StringWriter writer = new StringWriter();
    PrintWriter printWriter = new PrintWriter(writer);

    public Host(String hostname, String portNumber) {
        this.hostname = hostname;
        this.portNumber = portNumber;
        init();
    }

    public String getHostname() {
        return hostname;
    }

    public String getPortNumber() {
        return portNumber;
    }

    public Socket getSocket() {
        return socket;
    }

    private void init() {
        try {
            InetAddress addr = InetAddress.getByName(this.hostname);
            int port = Integer.parseInt(portNumber);
            socket = new Socket(addr, port);
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
        } catch (UnknownHostException uHE) {
            uHE.printStackTrace(printWriter);
            new ErrorWindow(writer.toString());
        } catch (IOException iOE) {
            iOE.printStackTrace(printWriter);
            new ErrorWindow(writer.toString());
        }
    }


    public void sendMessageToServer(Message message) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(message);
            dos.writeBytes(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace(printWriter);
            new ErrorWindow(writer.toString());
        } catch (IOException iOE) {
            iOE.printStackTrace(printWriter);
            new ErrorWindow(writer.toString());
        }
    }
}
