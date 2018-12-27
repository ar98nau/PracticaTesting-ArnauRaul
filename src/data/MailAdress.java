package data;
import Exception.WrongInputException;


/**
 * The Mail of a person that participates in an election.
 */

final public class MailAdress {
    private final String mail;

    public MailAdress(String mail) throws WrongInputException {
        if (mail != null) {
            this.mail = mail;
        } else {
            throw new WrongInputException();
        }
    }

    public String getMail() { return mail; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MailAdress mail1 = (MailAdress) o;
        return mail.equals(mail1.mail);
    }

    @Override
    public int hashCode() { return mail.hashCode(); }

    @Override
    public String toString() {
        return "Mail{" + "mail='" + mail + '\'' + '}';
    }
}
