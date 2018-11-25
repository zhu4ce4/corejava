package SOC;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerServer implements Runnable {

    Socket newSoc;
    private static int serverNum = 1;
    private static String serverName ="������"+ serverNum;

    public ServerServer(Socket aSoc) {
        newSoc = aSoc;
        serverNum++;
    }

    @Override
    public void run() {

        Thread sendThread = SendAndReceiveThread.getSendThread(newSoc, serverName);
        sendThread.start();

        Thread receiveThread = SendAndReceiveThread.getReceiveThread(newSoc);
        receiveThread.start();

        try {
            sendThread.join();
            receiveThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


class ServerServerTest {
    public static void main(String[] args) {
        try (ServerSocket ss = new ServerSocket(8189)) {
            while (true) {
                Socket newSoc = ss.accept();
                Runnable r = new ServerServer(newSoc);
                Thread t = new Thread(r);
                t.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
