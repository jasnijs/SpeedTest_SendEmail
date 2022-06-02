package speed.test;


import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream in = null;

    private OutputStream out = null;

    public Server (int port) {
        try {
            server = new ServerSocket(port);
            System.out.println("Server started");

            System.out.println("Waiting for client");

            Socket socket = server.accept();
            System.out.println("Client accepted");

            OutputStream out = socket.getOutputStream();

            byte[] bytes = new byte[10 * 1024];

            while (true) {
                out.write(bytes);
            }
        }  catch (IOException i) {
            System.out.println(i);
        }

    }
}

