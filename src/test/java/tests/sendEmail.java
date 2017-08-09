package tests;

import org.testng.annotations.Test;

import java.util.Date;
import java.util.Properties;
import javax.activation.*;
import javax.mail.*;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class sendEmail {
@Test
    public void sendEmail() {
    // Recipient's email ID needs to be mentioned.
    String to = "iryna.melnik@ria.com";

    // Sender's email ID needs to be mentioned
    String from = "iryna.melnik@ria.com";
    final String username = "manishaspatil";//change accordingly
    final String password = "vtkmybr1991";//change accordingly

    // Assuming you are sending email through relay.jangosmtp.net
    String host = "relay.jangosmtp.net";

    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", host);
    props.put("mail.smtp.port", "25");

    // Get the Session object.
    Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

    try {
        // Create a default MimeMessage object.
        Message message = new MimeMessage(session);

        // Set From: header field of the header.
        message.setFrom(new InternetAddress(from));

        // Set To: header field of the header.
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(to));

        // Set Subject: header field
        message.setSubject("Testing Subject");

        // Now set the actual message
        message.setText("Hello, this is sample for to check send " +
                "email using JavaMailAPI ");

        // Send message
        Transport.send(message);

        System.out.println("Sent message successfully....");

    } catch (MessagingException e) {
        throw new RuntimeException(e);
    }
    }

}
