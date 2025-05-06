package kacper.book_tracker.dto;

import lombok.Data;

@Data
public class BookListResponseDto {
    private int id;
    private String name;
    private int numberOfBooks;
}
