package Test;
import Exception.WrongInputException;
import data.DigitalSignature;
import data.MailAddress;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.MailerService;
import services.MailerServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

public class MailerServiceImplTest {
    private MailerService mail;
    private DigitalSignature signature;
    private MailAddress address;

    @BeforeEach
    void init(){
        mail = new MailerServiceImpl();
        try {
            signature = new DigitalSignature("test signature");
            address = new MailAddress("test_receiver@gmail.com");
        } catch (WrongInputException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testNotException(){
        boolean throwed = false;
        try {
            mail.send(address, signature);
        }catch(Exception ex) {
            throwed = true;
        }
        assertFalse(throwed);
    }

    }
