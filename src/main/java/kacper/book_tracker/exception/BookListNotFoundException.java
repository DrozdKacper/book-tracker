package kacper.book_tracker.exception;

public class BookListNotFoundException extends RuntimeException{
    public BookListNotFoundException(String message) {
        super(message);
    }
}
