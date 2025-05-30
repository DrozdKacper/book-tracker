package kacper.book_tracker.error;

import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
public class ApiError {
    private int status;
    private String message;
    private long timestamp;

    public ApiError(HttpStatus status, String message) {
        this.status = status.value();
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }
}
