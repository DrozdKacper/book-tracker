package kacper.book_tracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookListItemDto {
    private int id;
    private String title;
    private String author;
    private String genre;
    private Double averageRating;
    private Long countReviews;
}
