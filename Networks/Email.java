import java.io.*;
import javax.net.ssl.*;
import java.util.*;

class Email {
    private static DataOutputStream dos;
    public static BufferedReader br;

    public static void main(String argv[]) throws Exception {
        String user = "s2210776132@ru.ac.bd";
        String pass = "zcrltotffussgmgl";

        String username = Base64.getEncoder().encodeToString(user.getBytes());
        String password = Base64.getEncoder().encodeToString(pass.getBytes());

        SSLSocket s = (SSLSocket) SSLSocketFactory.getDefault().createSocket("smtp.gmail.com", 465);
        dos = new DataOutputStream(s.getOutputStream());
        br = new BufferedReader(new InputStreamReader(s.getInputStream()));

        System.out.println("Server: " + br.readLine());
        send("EHLO smtp.gmail.com\r\n");
        String line;
        while((line = br.readLine()) != null) {
            System.out.println("Server: " + line);
            if (line.startsWith("250"))
                break;
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

        send("RCPT TO:<rafymdmehedihasan@gmail.com>\r\n");
        System.out.println("Server: " + br.readLine());

        send("RCPT TO:<kamrulhasan87865@gmail.com>\r\n");
        System.out.println("Server: " + br.readLine());

        send("DATA\r\n");
        System.out.println("Server: " + br.readLine());

        send("From: s2210776132@ru.ac.bd\r\n");
        send("To: mdmehedihasanrafy747@gmail.com\r\n");
        send("Cc: rafymdmehedihasan@gmail.com\r\n");
        send("Subject: Email test\r\n");
        send("\r\n");

        send("THIS IS A TEST EMAIL. THANK YOU.\r\n");

        send(".\r\n");
        System.out.println("Server: " + br.readLine());

        send("QUIT\r\n");
        System.out.println("Server: " + br.readLine());

    }

    public static void send(String s) throws Exception{
        dos.writeBytes(s);
        dos.flush();
        Thread.sleep(500);
        System.out.println("Client: " + s);
    }

}

