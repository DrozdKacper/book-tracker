package kacper.book_tracker.dto;

import lombok.Data;

@Data
public class ReviewRequestDto {
    private String title;
    private String text;
    private Integer rating;
    private int userBookId;
}