package data;
import Exception.WrongInputException;

/**
 * The Digital Signature of a person that participates in an election.
 */

final public class DigitalSignature {
    private final String signature;

    public DigitalSignature(String signature) throws WrongInputException {
        if (signature != null) {
            this.signature = signature;
        } else {
            throw new WrongInputException();
        }
    }

    public String getSignature() { return signature; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DigitalSignature signature1 = (DigitalSignature) o;
        return signature.equals(signature1.signature);
    }

    @Override
    public int hashCode() { return signature.hashCode(); }

    @Override
    public String toString() {
        return "Digital Signature{" + "signature='" + signature + '\'' + '}';
    }
}
