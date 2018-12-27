package services;

import data.MailAddress;
import data.DigitalSignature;

/**
 * External service for sending mails
 */

public interface MailerService {
    void send(MailAddress address, DigitalSignature signature);
}
