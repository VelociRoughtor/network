import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            // 1. Connect to server
            Socket socket = new Socket("localhost", 5000);

            // 2. Input from keyboard
            Scanner sc = new Scanner(System.in);

            // 3. Send data to server
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            // 4. Receive data from server
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("Connected to Server.");
            System.out.println("Enter command:");
            System.out.println("Hi / Date / Time / IP / Close");

            // 5. Send message
            while (true) {
                System.out.println("Client: ");
                String message = sc.nextLine();
                output.println(message);

                // 6. Receive reply
                String response = input.readLine();
                System.out.println("Server Response: " + response);

                if (message.equalsIgnoreCase("Close")) {
                    break;
                }
            }

            // 7. Close connection
            socket.close();
            System.out.println("Client Closed.");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
