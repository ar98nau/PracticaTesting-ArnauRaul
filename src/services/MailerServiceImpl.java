package services;

import data.DigitalSignature;
import data.MailAddress;
import Exception.WrongInputException;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class MailerServiceImpl implements MailerService {
    MailAddress mail = new MailAddress("localhost");
    public MailerServiceImpl() throws WrongInputException {
    }

    @Override
    public void send(MailAddress address, DigitalSignature signature) {
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", "localhost");

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress("organismeElectoral@gmail.com"));

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
