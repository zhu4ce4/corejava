package SOC;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.StandardProtocolFamily;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.concurrent.TransferQueue;

public class ThreadServer {
    public static void main(String[] args) {
        try (ServerSocket s = new ServerSocket(8189)) {
            while (true) {
                Socket getASock = s.accept();
                Runnable r = new ThreadEchoHandler(getASock);
                Thread t = new Thread(r);
                t.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


class ThreadEchoHandler implements Runnable {

    private Socket incoming;
    private static int serverNum=1;
    private String serverName = "服务器" + serverNum;
    public ThreadEchoHandler(Socket incomingSock) {
        incoming = incomingSock;
        serverNum++;
    }

    @Override
    public void run() {
//        try (InputStream inStream = incoming.getInputStream(); OutputStream outStream = incoming.getOutputStream()) {
//            Scanner in = new Scanner(inStream, StandardCharsets.UTF_8);
//            PrintWriter out = new PrintWriter(new OutputStreamWriter(outStream, StandardCharsets.UTF_8), true);
//            out.println("欢迎登船！输入bye跳船");
//            boolean done = false;
//            while (!done && in.hasNextLine()) {
//                String line = in.nextLine();
//                out.println("回声系统：" + line);
//                if ("bye".equalsIgnoreCase(line.trim())) {
//                    done = true;
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        Thread sendThread = SendAndReceiveThread.getSendThread(incoming, serverName);
        sendThread.start();

        Thread receiveThread = SendAndReceiveThread.getReceiveThread(incoming);
        receiveThread.start();

//        try {
//            sendThread.join();
//            receiveThread.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
