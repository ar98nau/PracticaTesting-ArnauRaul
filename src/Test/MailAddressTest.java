package Test;

import static org.junit.jupiter.api.Assertions.*;
import Exception.WrongInputException;
import data.MailAddress;
import org.junit.jupiter.api.Test;

public class MailAddressTest {

    @Test
    void exceptionTesting() {
        assertThrows(WrongInputException.class, () -> new MailAddress(null));
    }

}