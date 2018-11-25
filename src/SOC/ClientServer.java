package SOC;

import java.io.IOException;
import java.net.Socket;

public class ClientServer {
    public static void main(String[] args){
        String serverName = "localhost";
        int port = 8189;

        try (Socket s = new Socket(serverName, port)) {

                Thread sendThread = SendAndReceiveThread.getSendThread(s,"客户");
                sendThread.start();

                Thread receiveThread = SendAndReceiveThread.getReceiveThread(s);
                receiveThread.start();

            try {
                sendThread.join();
                receiveThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
