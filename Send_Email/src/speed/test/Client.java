package speed.test;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private Socket socket;
    private DataInputStream input;
    private DataOutputStream out;

    public Client(String address, int port) {
        try {
            socket = new Socket(address, port);
            System.out.println("Connected");

            input = new DataInputStream(System.in);

            out = new DataOutputStream(socket.getOutputStream());
        } catch (UnknownHostException u) {
            System.out.println(u);
        } catch (IOException i) {
            System.out.println(i);
        }
    }
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("192.168.0.100", 5050);
            InputStream input = socket.getInputStream();
            long total = 0;
            long start = System.currentTimeMillis();

            byte[] bytes = new byte[10240];
            for(int i=1;;i++) {
                int read = input.read(bytes);
                if (read < 0) break;
                total += read;
                if (i % 500000 == 0) {
                    long cost = System.currentTimeMillis() - start;
                    System.out.printf("Read %,d bytes, speed: %,d MB/s%n", total, total/cost/1000);
                } else if (read == 0) {
                    System.out.println("epasts");
                }
            }
        } catch (UnknownHostException u) {
            System.out.println(u);
        } catch (IOException i) {
            System.out.println(i);
        }
    }
}




