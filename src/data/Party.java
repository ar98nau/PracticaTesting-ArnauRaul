package data;
import Exception.WrongInputException;

/**
 * The name of a party that participates in an election.
 */

final public class Party {
    private final String name;

    public Party(String name) throws WrongInputException {
         if (name != null) {
             this.name = name;
         } else {
             throw new WrongInputException();
         }
    }

    public String getName() { return name; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Party party1 = (Party) o;
        return name.equals(party1.name);
    }

    @Override
    public int hashCode() { return name.hashCode(); }

    @Override
    public String toString() {
        return "Party{" + "name='" + name + '\'' + '}';
    }
}
