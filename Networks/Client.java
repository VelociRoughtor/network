import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8080);

            BufferedReader userInput =
                    new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out =
                    new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in =
                    new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String message;

            while (true) {
                System.out.print("You: ");
                message = userInput.readLine();

                out.println(message);

                if (message.equalsIgnoreCase("exit")) {
                    break;
                }

                String reply = in.readLine();
                System.out.println("Server: " + reply);
            }

            socket.close();
            System.out.println("Client disconnected.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

