package kacper.book_tracker.exception;

public class AccessDeniedException extends RuntimeException
{
    public AccessDeniedException(String message) {
        super(message);
    }
}
