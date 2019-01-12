package Test;

import Exception.WrongInputException;
import data.MailAddress;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class MailAddressTest {

    @Test
    void exceptionTesting() {
        assertThrows(WrongInputException.class, () -> new MailAddress(null));
    }

}