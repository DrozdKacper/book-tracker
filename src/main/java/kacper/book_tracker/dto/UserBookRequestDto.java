package kacper.book_tracker.dto;

import lombok.Data;

@Data
public class UserBookRequestDto {
    private int bookId;
    private int bookListId;
    private Integer rating;
    private Integer currentPage;
    private String notes;
    private Boolean finished;
}