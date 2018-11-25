package SOC;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

//用dataOutputStream或printWriter
public class SendAndReceiveThread {


    public static Thread getSendThread(Socket s,String MyName) {
        Thread sendThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try (OutputStream os = s.getOutputStream(); Scanner send = new Scanner(System.in,StandardCharsets.UTF_8)) {
                    while (true) {
                        String line = send.nextLine();
//                            DataOutputStream pw = new DataOutputStream(os);
                        PrintWriter pw = new PrintWriter(new OutputStreamWriter(os,StandardCharsets.UTF_8), true);
//                            pw.writeUTF(line);
                        if ("exit".equalsIgnoreCase(line)) {
                            break;
                        }
                        pw.println(MyName+"："+line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        return sendThread;
    }

    public static Thread getReceiveThread(Socket s) {
        Thread receiveThread = new Thread(new Runnable() {
            @Override
            public void run() {
//                    try (InputStream is = inc.getInputStream(); DataInputStream receive = new DataInputStream(is)) {
//                try (InputStream is = s.getInputStream(); Scanner receive = new Scanner(is)) {
                try (InputStream is = s.getInputStream(); BufferedReader receive=new BufferedReader(new InputStreamReader(is,StandardCharsets.UTF_8))) {
//                    while (receive.hasNextLine()) {
                    while (true) {
//                            String line = receive.readUTF();
//                        String line = receive.nextLine();
                        String line = receive.readLine();
                        System.out.println(line);
                        if (line.contains("bye")) {
                            break;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        return receiveThread;
    }
}
