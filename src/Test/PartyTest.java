package Test;

import Exception.WrongInputException;
import data.Party;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class PartyTest {

    @Test
    void exceptionTesting() {
        assertThrows(WrongInputException.class, () -> new Party(null));
    }

}
