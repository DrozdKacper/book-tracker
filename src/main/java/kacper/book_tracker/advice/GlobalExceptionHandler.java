package kacper.book_tracker.advice;

import kacper.book_tracker.error.ApiError;
import kacper.book_tracker.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiError> handleAccessDenied(AccessDeniedException exception) {
        ApiError error= new ApiError(HttpStatus.UNAUTHORIZED, exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(BookListNotFoundException.class)
    public ResponseEntity<ApiError> handleBookListNotFound(BookListNotFoundException exception) {
        ApiError error = new ApiError(HttpStatus.NOT_FOUND, exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ApiError> handleBookNotFound(BookNotFoundException exception) {
        ApiError error = new ApiError(HttpStatus.NOT_FOUND, exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidRequestDataException.class)
    public ResponseEntity<ApiError> handleInvalidRequestData(InvalidRequestDataException exception) {
        ApiError error = new ApiError(HttpStatus.BAD_REQUEST, exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ApiError> handleUserAlreadyExists(UserAlreadyExistsException exception) {
        ApiError error = new ApiError(HttpStatus.CONFLICT, exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserBookAlreadyExistsException.class)
    public ResponseEntity<ApiError> handleUserBookAlreadyExists(UserBookAlreadyExistsException exception) {
        ApiError error = new ApiError(HttpStatus.CONFLICT, exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserBookNotFoundException.class)
    public ResponseEntity<ApiError> handleUserBookNotFound(UserBookNotFoundException exception) {
        ApiError error = new ApiError(HttpStatus.NOT_FOUND, exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiError> handleUserNotFound(UserNotFoundException exception) {
        ApiError error = new ApiError(HttpStatus.NOT_FOUND, exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ReviewNotFoundException.class)
    public ResponseEntity<ApiError> handleReviewNotFound(ReviewNotFoundException exception) {
        ApiError error = new ApiError(HttpStatus.NOT_FOUND, exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
