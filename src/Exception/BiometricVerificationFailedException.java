package Exception;

public class BiometricVerificationFailedException extends Exception {
    public BiometricVerificationFailedException() { super(); }
    public BiometricVerificationFailedException(String message) { super(message); }
    public BiometricVerificationFailedException(String message, Throwable cause) { super(message, cause); }
    public BiometricVerificationFailedException(Throwable cause) { super(cause); }
}
