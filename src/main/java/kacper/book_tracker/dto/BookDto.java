package kacper.book_tracker.dto;

import lombok.Data;

import java.util.Date;

@Data
public class BookDto {
    private int id;
    private String title;
    private String author;
    private String description;
    private String isbn;
    private String publisher;
    private Date publicationDate;
    private String genre;
    private Integer pageCount;


}
