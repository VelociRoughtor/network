import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("Server started. Waiting for client...");

            Socket socket = serverSocket.accept();
            System.out.println("Client connected.");

            BufferedReader in =
                    new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out =
                    new PrintWriter(socket.getOutputStream(), true);

            String message;

            while (true) {
                message = in.readLine();

                if (message == null || message.equalsIgnoreCase("exit")) {
                    break;
                }

                System.out.println("Client: " + message);
                out.println("Received: " + message);
            }

            socket.close();
            serverSocket.close();
            System.out.println("Server stopped.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

