package kacper.book_tracker.exception;

public class UserBookNotFoundException extends RuntimeException{
    public UserBookNotFoundException(String message) {
        super(message);
    }
}
