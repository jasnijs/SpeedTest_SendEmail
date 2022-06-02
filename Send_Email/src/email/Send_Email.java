package email;


// Imports are related to javax.mail.jar that is imported into program separately "JavaMail"
import javax.mail.*;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Send_Email {
    public static void sendEmail(String recipient) throws Exception {

        System.out.println("Attempting to send an email...");
        Properties properties = new Properties();

        // mail.smtp.auth - defines whether authentication is needed or not (ex. gmail needs id and pass)
        // mail.smtp.starttls.enable - encryption
        // mail.smtp.host - mail.inbox.lv / smtp.gmail.com etc.
        // mail.smtp.port - 587 / 465

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "mail.inbox.com");
        properties.put("mail.smtp.port", "465");

        // Your account information
        String myAcc = "";
        String pass = "";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAcc, pass);
            }
        });

        // Message properties set bellow for clarity of main process
        Message message = prepareMessage(session, myAcc, recipient);

        Transport.send(message);
        System.out.println("Message was sent!");
    }

    private static Message prepareMessage(Session session, String myAcc, String recipient) {
//        Message message = new MimeMessage(session);
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAcc));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("Test");
            message.setText("Hello, \n This is a test message sent from Java program.");
            return message;
        } catch (Exception e) {
            Logger.getLogger(Send_Email.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
}