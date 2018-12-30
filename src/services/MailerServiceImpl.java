package services;

import data.DigitalSignature;
import data.MailAddress;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class MailerServiceImpl implements MailerService {
    Properties properties;
    MimeMessage message;

    public MailerServiceImpl() {
        properties = System.getProperties();
        // Setup mail server
        properties.setProperty("mail.smtp.host", "localhost");
        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);
        // Create a default MimeMessage object.
        message = new MimeMessage(session);
        // Set From: header field of the header.
        try {
            message.setFrom(new InternetAddress("organismeElectoral@gmail.com"));
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void send(MailAddress address, DigitalSignature signature) {
        try {
            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(address.getMail()));
            // Set Subject: header field
            message.setSubject("Codi Comprovació");
            // Now set the actual message
            message.setText(signature.toString());
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");

        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
