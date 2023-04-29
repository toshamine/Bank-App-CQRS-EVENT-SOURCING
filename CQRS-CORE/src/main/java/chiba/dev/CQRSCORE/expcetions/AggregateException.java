package chiba.dev.CQRSCORE.expcetions;

public class AggregateException extends RuntimeException{

    public static final AggregateException NOT_FOUND = new AggregateException("Aggregate not found");

    public AggregateException(String message) {
        super(message);
    }
}
