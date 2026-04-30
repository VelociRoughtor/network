import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Base64;
import java.util.Enumeration;

public class Email {
    private static DataOutputStream dos;
    public static BufferedReader br;
    public static void main(String[] args) {
        try {
            String user = "s2210776132@ru.ac.bd";
            String pass = "uikyzhbrhqmyxvxo";
            String username = Base64.getEncoder().encodeToString(user.getBytes());
            String password = Base64.getEncoder().encodeToString(pass.getBytes());
            // =========================
            // SMTP SSL connection
            // =========================
            SSLSocket s = (SSLSocket) SSLSocketFactory.getDefault()
                    .createSocket("smtp.gmail.com", 465);

            dos = new DataOutputStream(s.getOutputStream());
            br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            System.out.println("Server: " + br.readLine());
            // =========================
            // EHLO
            // =========================
            send("EHLO smtp.gmail.com\r\n");
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("Server: " + line);
                if (line.charAt(3) == ' ') break;
            }
            // =========================
            // AUTH LOGIN
            // =========================
            send("AUTH LOGIN\r\n");
            System.out.println("Server: " + br.readLine());
            send(username + "\r\n");
            System.out.println("Server: " + br.readLine());
            send(password + "\r\n");
            System.out.println("Server: " + br.readLine());
            // =========================
            // MAIL SETUP
            // =========================
            send("MAIL FROM:<s2210776132@ru.ac.bd>\r\n");
            System.out.println("Server: " + br.readLine());
            send("RCPT TO:<mdmehedihasanrafy747@gmail.com>\r\n");
            System.out.println("Server: " + br.readLine());
            send("RCPT TO:<kamrulhasan67865@gmail.com>\r\n");
            System.out.println("Server: " + br.readLine());
            send("RCPT TO:<kamrulhasan87865@gmail.com>\r\n");
            System.out.println("Server: " + br.readLine());
            // =========================
            // DATA START
            // =========================
            send("DATA\r\n");
            System.out.println("Server: " + br.readLine());
            send("FROM: s22107776132@ru.ac.bd\r\n");
            send("TO: mdmehedihasanrafy747@gmail.com\r\n");
            send("CC: kamrulhasan67865@gmail.com\r\n");
            send("Subject: Email Test\r\n");
            send("MIME-Version: 1.0\r\n");
            send("Content-Type: text/plain; charset=UTF-8\r\n");
            send("\r\n");
            // =========================
            // SYSTEM INFO
            // =========================
            InetAddress ip = InetAddress.getLocalHost();
            String macAddress = getMacAddress();
            send("This is a TEST EMAIL from Rafy.\r\n");
            send("\r\n");
            send("MAC Address: " + macAddress + "\r\n");
            send("IP Address: " + ip.getHostAddress() + "\r\n");
            // End of email
            send(".\r\n");
            System.out.println("Server: " + br.readLine());
            // Quit
            send("QUIT\r\n");
            System.out.println("Server: " + br.readLine());
            dos.close();
            br.close();
            s.close();
            System.out.println("Email sent successfully.");
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    // =========================
    // Send helper
    // =========================
    public static void send(String s) throws Exception {
        dos.writeBytes(s);
        dos.flush();
        System.out.print("Client: " + s);
    }

    // =========================
    // Reliable MAC fetch
    // =========================
    public static String getMacAddress() {
        try {
            Enumeration<NetworkInterface> networks = NetworkInterface.getNetworkInterfaces();
            while (networks.hasMoreElements()) {
                NetworkInterface ni = networks.nextElement();
                if (ni == null || !ni.isUp() || ni.isLoopback()) continue;
                byte[] mac = ni.getHardwareAddress();
                if (mac == null) continue;
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < mac.length; i++) {
                    sb.append(String.format("%02X", mac[i]));
                    if (i != mac.length - 1) sb.append("-");
                }
                return sb.toString();
            }
        } catch (Exception e) {
            return "Unavailable";
        }
        return "Unavailable";
    }
}
