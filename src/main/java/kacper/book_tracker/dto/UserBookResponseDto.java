package kacper.book_tracker.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserBookResponseDto {
    private int id;
    private String bookTitle;
    private String bookAuthor;
    private Integer rating;
    private Integer currentPage;
    private String notes;
    private Boolean finished;
    private Date addedAt;
}