package network;

import application.ErrorWindow;
import application.Letter;
import application.Message;
import application.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.ZonedDateTime;

public class Host {

    private String hostname;
    private int portNumber;

    private DataInputStream dis;
    private DataOutputStream dos;
    private Socket socket;
    private StringWriter writer = new StringWriter();
    private PrintWriter printWriter = new PrintWriter(writer);
    private ObjectMapper mapper = new ObjectMapper();
    private boolean request = false;
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
            dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
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

            byte[] bytes = new byte[1024];
                int inputLength = dis.read(bytes);
                Gson g = new Gson();
                String input = new String(bytes);
                String inputHelper = input.replaceAll(" ", "");
                if(input.charAt(0) =='{') {
                    System.out.println(inputHelper);
                    Letter letter = g.fromJson(inputHelper, Letter.class);
                    return letter.parseToMessage();
                }else if(request) {
                    byte[] bytesToRead = new byte[1024];
                    int bytesRead;
                    String path ="big.txt";
                    File f =new File(path);
                    OutputStream  fileStream = new FileOutputStream(f);
                    fileStream.write(bytes);
                    f.createNewFile();
                    if(f.exists()) {
                        while ((bytesRead = dis.read(bytesToRead)) == bytesToRead.length) {
                            fileStream.write(bytesToRead,0,bytesRead);
                        }
                        Message message = new Message();
                        message.setMessage("File name big.txt");
                        message.setTime(ZonedDateTime.now());
                        message.setUser(new User("Server"));
                        return message;
                    }
                    request = false;
                }
        } catch (IOException e) {
            e.printStackTrace(printWriter);
            new ErrorWindow(writer.toString());
        }
        return new Message(new User("User"), "blank");
    }

    public void requestFile(){
        try {
            request = true;
            dos.write("File".getBytes());
            dos.write("big.txt".getBytes());
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void sendMessageToServer(Message message) {

        try {
            Gson gson = new Gson();
            Letter letter = new Letter(message.getUser(),message.getMessage(),message.getTime());
            String json = gson.toJson(letter);
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
