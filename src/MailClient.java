import javax.mail.*;
import javax.mail.internet.*;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class MailClient {

    private static final Object lock = new Object();

    public static void sendEmail(String to, String subject, String body) {
        String host = "smtp.gmail.com";
        final String user = "shawkatshifat111@gmail.com"; 
        final String password = "wimr fuod zppq codb";

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        try (FileOutputStream fos = new FileOutputStream("email.txt");
             BufferedOutputStream bos = new BufferedOutputStream(fos)) {
            String emailContent = "To: " + to + "\nSubject: " + subject + "\nBody: " + body;
            bos.write(emailContent.getBytes());
            bos.flush();
        } catch (IOException e) {
            Log.logError("Failed to write", e);
        }

        Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(user, password);
                }
            });

        synchronized (lock) { 
            try {
                MimeMessage message = new MimeMessage(session);

                message.setFrom(new InternetAddress(user));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                message.setSubject(subject);
                message.setText(body);

                Transport.send(message);
                Log.logInfo("Successfully Sent to " + to);

            } catch (MessagingException e) {
                Log.logError("Message not Sent to " + to, e);
            }
        }
    }
}
