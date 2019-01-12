package services;

import data.DigitalSignature;
import data.MailAddress;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class MailerServiceImpl implements MailerService {

    public MailerServiceImpl() {
    }

    @Override
    public void send(MailAddress address, DigitalSignature signature) {
        System.out.println("S'ha enviat la signatura digital " + signature.toString() + " a l'adreça " + address.toString());
    }
}
