package Test;

import Exception.WrongInputException;
import data.DigitalSignature;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DigitalSignatureTest {

    @Test
    void exceptionTesting() {
        assertThrows(WrongInputException.class, () -> new DigitalSignature(null));
    }

}