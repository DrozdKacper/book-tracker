package kacper.book_tracker.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ReviewResponseDto {
    private int id;
    private String title;
    private String text;
    private Integer rating;
    private Date createdAt;
    private String bookTitle;
    private String username;
}
