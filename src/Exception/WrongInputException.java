package Exception;

public class WrongInputException extends Exception{
    public WrongInputException() { super(); }
    public WrongInputException(String message) { super(message); }
    public WrongInputException(String message, Throwable cause) { super(message, cause); }
    public WrongInputException(Throwable cause) { super(cause); }
}
