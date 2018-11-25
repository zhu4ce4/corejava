package SOC;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientServer {
    public static void main(String[] args) throws UnknownHostException, IOException {
        String serverName = "localhost";
        int port = 8189;

        try (Socket s = new Socket(serverName, port)) {

            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try (OutputStream os = s.getOutputStream(); Scanner send = new Scanner(System.in)) {
                        while (true) {
                            String line = send.nextLine();
//                            DataOutputStream pw = new DataOutputStream(os);
                            PrintWriter pw = new PrintWriter(new OutputStreamWriter(os), true);
//                            pw.writeUTF(line);
                            if ("exit".equalsIgnoreCase(line)) {
                                break;
                            }
                            pw.println(line);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            t1.start();


            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
//                    try (InputStream is = inc.getInputStream(); DataInputStream receive = new DataInputStream(is)) {
                    try (InputStream is = s.getInputStream(); Scanner receive = new Scanner(is)) {
                        while (receive.hasNextLine()) {
//                            String line = receive.readUTF();
                            String line = receive.nextLine();
                            System.out.println("服务器：" + line);
                            if (line.contains("bye")) {
                                break;
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            t2.start();

            try {
                t1.join();
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

