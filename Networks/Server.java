import java.io.*;
import java.net.*;
import java.time.*;

public class Server {
    public static void main(String[] args) {
        try{
            // 1. Create server socket on port 5000
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Server is waiting...");
            System.out.println("Waiting for client...");

            // 2. Accept Client Connection
            Socket socket = serverSocket.accept();
            System.out.println("Client connected.");

            // 3. Input stream from Client
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // 4. Output stream to Client
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            // 5. Read message from client
            String message;
            while (true) {
                message = input.readLine();
                if (message == null) {
                    break;
                }
                System.out.println("Client says: " + message);
                if (message.equalsIgnoreCase("Close")) {
                    output.println("Connection close");
                    break;
                }

                // 6. Process Client request
                if (message.equalsIgnoreCase("Hi")) {
                    output.println("Hello");
                } else if (message.equalsIgnoreCase("Date")) {
                    output.println("Current Date: " + LocalDate.now());
                } else if (message.equalsIgnoreCase("Time")) {
                    output.println("Current Time: " + LocalTime.now());
                } else if (message.equalsIgnoreCase("IP")) {
                    String ip = InetAddress.getLocalHost().getHostAddress();
                    output.println("IP: " + ip);
                } else {
                    output.println("Invalid Command");
                }
            }

            // 7. close everything
            socket.close();
            serverSocket.close();
            System.out.println("Server closed.");
        } catch(Exception e) {
            System.out.println(e);
        }
    }
}
