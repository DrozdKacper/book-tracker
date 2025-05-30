package kacper.book_tracker.exception;

public class UserBookAlreadyExistsException extends RuntimeException {
    public UserBookAlreadyExistsException(String message) {
        super(message);
    }
}
