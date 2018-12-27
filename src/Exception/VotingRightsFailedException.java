package Exception;

public class VotingRightsFailedException extends Exception {
    public VotingRightsFailedException() { super(); }
    public VotingRightsFailedException(String message) { super(message); }
    public VotingRightsFailedException(String message, Throwable cause) { super(message, cause); }
    public VotingRightsFailedException(Throwable cause) { super(cause); }
}

