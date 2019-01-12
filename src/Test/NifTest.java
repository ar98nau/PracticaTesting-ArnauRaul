package Test;

import Exception.WrongInputException;
import data.Nif;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class NifTest {

    @Test
    void exceptionTesting() {
        assertThrows(WrongInputException.class, () -> new Nif(null));
    }

}