package data;
import Exception.WrongInputException;

import java.util.ArrayList;


/**
 * The NIF of a person that participates in an election.
 */

final public class Nif {
    private final String nif;

    public Nif(String nif) throws WrongInputException {
        if (nif == null) throw new WrongInputException();
        if (checkNif(nif)){
            this.nif = nif;
        } else {
            throw new WrongInputException();
        }

    }

    public String getNif() { return nif; }

    private boolean checkNif (String nif) {
        /*suposem com a correcte un NIF del format de 8 digits seguits d'una lletra.
        Per exemple: 123456789A */
        if (nif.length() != 9) return false;
        String numbers = this.nif.substring(0, 7);
        String letter = this.nif.substring(8, 8);
        if (!isNum(numbers)) return false;
        if (!isAlpha(letter)) return false;
        return  true;
    }

    private boolean isNum(String strNum) {
        boolean ret = true;
        try {
            Double.parseDouble(strNum);
        }catch (NumberFormatException e) {
            ret = false;
        }
        return ret;
    }

    private boolean isAlpha(String name) {
        return name.matches("[a-zA-Z]+");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nif nif1 = (Nif) o;
        return nif.equals(nif1.nif);
    }

    @Override
    public int hashCode() { return nif.hashCode(); }

    @Override
    public String toString() {
        return "Nif{" + "nif='" + nif + '\'' + '}';
    }
}
