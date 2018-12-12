package network;

import application.ErrorWindow;
import application.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Host {

    private String hostname;
    private int portNumber;

    private DataInputStream dis;
    private DataOutputStream dos;
    private Socket socket;
    private StringWriter writer = new StringWriter();
    private PrintWriter printWriter = new PrintWriter(writer);
    private ObjectMapper mapper = new ObjectMapper();

    public Host(String hostname, int portNumber) {
        this.hostname = hostname;
        this.portNumber = portNumber;
        init();
    }

    public DataInputStream getDis() {
        return dis;
    }

    public Socket getSocket() {
        return socket;
    }

    private void init() {
        try {
            InetAddress addr = InetAddress.getByName(this.hostname);
            socket = new Socket(addr, portNumber);
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

    public Message receiveNewMessageFromServer() {
        try {
            String output = dis.readUTF();
            Gson g = new Gson();
            Message message = g.fromJson(output, Message.class);
            return message;
        } catch (IOException e) {
            e.printStackTrace(printWriter);
            new ErrorWindow(writer.toString());
        }
        return new Message(null, "");
    }

    public void sendMessageToServer(Message message) {

        try {
            Gson gson = new Gson();
            String json = gson.toJson(message);
            byte[] utf8json = json.getBytes("UTF-8");
            dos.write(utf8json);
        } catch (JsonProcessingException e) {
            e.printStackTrace(printWriter);
            new ErrorWindow(writer.toString());
        } catch (IOException iOE) {
            iOE.printStackTrace(printWriter);
            new ErrorWindow(writer.toString());
        }
    }
}
