package data;
import Exception.WrongInputException;

import java.nio.charset.Charset;

/**
 * The Digital Signature which the electoral organism provides.
 */

final public class DigitalSignature {
    private final byte[] signature;

    public DigitalSignature(String signature) throws WrongInputException {
        if (signature != null) {
            this.signature = signature.getBytes(Charset.forName("UTF-8"));
        } else {
            throw new WrongInputException();
        }
    }

    public String getSignature() { return signature.toString(); }

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
    public String toString() { return new String(signature); }
}
