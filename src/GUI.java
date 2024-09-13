import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class GUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Mail Client");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("To:");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(100, 20, 250, 25);
        panel.add(userText);

        JLabel messageLabel = new JLabel("Message:");
        messageLabel.setBounds(10, 50, 80, 25);
        panel.add(messageLabel);

        JTextArea messageText = new JTextArea();
        messageText.setBounds(100, 50, 250, 100);
        panel.add(messageText);

        JButton sendButton = new JButton("Send");
        sendButton.setBounds(150, 160, 80, 25);
        panel.add(sendButton);

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String to = userText.getText();
                String message = messageText.getText();
                sendEmail(to, "Mail", message);
            }
        });
    }

    private static void sendEmail(String to, String subject, String body) {
        String host = "smtp.gmail.com";
        final String user = "shawkatshifat111@gmail.com"; 
        final String password = "wimr fuod zppq codb"; 

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587"); 
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); 

        Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(user, password);
                }
            });

        try {
            MimeMessage messageObj = new MimeMessage(session);

            messageObj.setFrom(new InternetAddress(user));
            messageObj.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            messageObj.setSubject(subject);
            messageObj.setText(body);

            Transport.send(messageObj);
            JOptionPane.showMessageDialog(null, "Successfully Sent!");

        } catch (MessagingException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed To Send: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
