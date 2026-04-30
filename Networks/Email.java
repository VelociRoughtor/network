import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import javax.net.ssl.*;
import java.util.*;

public class Email {
    private static DataOutputStream dos;
    public static BufferedReader br;

    public static void main(String[] args) {
        try {
            String user = "s2210776132@ru.ac.bd";
            String pass = "uikyzhbrhqmyxvxo";
            String username = Base64.getEncoder().encodeToString(user.getBytes());
            String password = Base64.getEncoder().encodeToString(pass.getBytes());

            SSLSocket s = (SSLSocket) SSLSocketFactory.getDefault().createSocket("smtp.gmail.com", 465);
            dos = new DataOutputStream(s.getOutputStream());
            br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            System.out.println("Server: " + br.readLine());

            send("EHLO smtp.gmail.com\r\n");
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("Server: " + line);
                if (line.startsWith("250 ")) {
                    break;
                }
            }

            send("AUTH LOGIN\r\n");
            System.out.println("Server: " + br.readLine());

            send(username + "\r\n");
            System.out.println("Server: " + br.readLine());

            send(password + "\r\n");
            System.out.println("Server: " + br.readLine());

            send("MAIL FROM:<s2210776132@ru.ac.bd>\r\n");
            System.out.println("Server: " + br.readLine());

            send("RCPT TO:<mdmehedihasanrafy747@gmail.com>\r\n");
            System.out.println("Server: " + br.readLine());

            send("RCPT TO:<kamrulhasan67865@gmail.com>\r\n");
            System.out.println("Server: " + br.readLine());

            send("RCPT TO:<kamrulhasan87865@gmail.com>\r\n");
            System.out.println("Server: " + br.readLine());

            send("DATA\r\n");
            System.out.println("Server: " + br.readLine());

            send("FROM: s22010776132@ru.ac.bd\r\n");
            send("TO: mdmehedihasanrafy747@gmail.com\r\n");
            send("CC: kamrulhasan67865@gmail.com\r\n");
            send("Subject: Email test\r\n");
            send("\r\n");

            send("This is a TEST EMAIL from Rafy. THANK YOU :)\r\n");

            send(".\r\n");
            System.out.println("Server: " + br.readLine());

            send("QUIT\r\n");
            System.out.println("Server: " + br.readLine());

            dos.close();
            br.close();
            s.close();

            System.out.println("Email was sent successfully.");

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public static void send(String s) throws Exception{
        dos.writeBytes(s);
        dos.flush();
        System.out.println("Client: " + s);
    }
}
