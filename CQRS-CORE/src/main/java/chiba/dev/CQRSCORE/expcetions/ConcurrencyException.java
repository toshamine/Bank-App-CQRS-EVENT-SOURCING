package chiba.dev.CQRSCORE.expcetions;

public class ConcurrencyException extends RuntimeException{

    public static final ConcurrencyException INCORRECT_VERSION = new ConcurrencyException("The version is uncorrect");

    public ConcurrencyException(String message) {
        super(message);
    }
}
