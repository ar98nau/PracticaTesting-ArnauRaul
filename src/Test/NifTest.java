package Test;

import static org.junit.jupiter.api.Assertions.*;
import Exception.WrongInputException;
import data.Nif;
import org.junit.jupiter.api.Test;

class NifTest {

    @Test
    void exceptionTesting() {
        assertThrows(WrongInputException.class, () -> new Nif(null));
    }

}